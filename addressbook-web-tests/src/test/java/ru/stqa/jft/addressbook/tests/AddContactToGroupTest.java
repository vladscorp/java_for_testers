package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

public class AddContactToGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup"));
        }
        if (app.db().entries().size() == 0) {
            EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                    .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                    .withWork("354332").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980");
            app.goTo().homePage();
            app.entry().create(entry, true);
        }
    }

    @Test
    public void testAddContactToGroup() {
        Entries before = app.db().entries();
        Groups groups = app.db().groups();
        EntryData entry = before.iterator().next();
        GroupData group = new GroupData();
        //проверка на привязку контакта сразу ко всем группам
        if (groups.size()==entry.getGroups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup"));
        }
        // проверка на присутствие в выбранной группе
        for (GroupData g : groups) {
            int i=0;
            for (GroupData e : entry.getGroups()) {
                if (g.equals(e)) {
                    break;
                } else i++;
            }
            if (i == entry.getGroups().size()) {
                group = g;
                break;
            }
        }
        //пришлось сортировать по группам, т.к. в списке All после привязки открывается пустая страница
        String id;
        if (entry.getGroups().size()==0) {
            id="[none]";
        } else {
            id = ("" + entry.getGroups().iterator().next().getId());
        }
        entry.inGroup(group);
        app.goTo().homePage();
        app.entry().addContactToGroup(entry, group, id);
    }


}
