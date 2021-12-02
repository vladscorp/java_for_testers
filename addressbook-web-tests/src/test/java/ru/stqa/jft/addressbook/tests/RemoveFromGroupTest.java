package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.GroupData;

public class RemoveFromGroupTest extends TestBase{

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
    public void testRemoveFromGroup() {
        Entries before = app.db().entries();
        Entries entries = new Entries();
        for (EntryData en : before) {
            if (en.getGroups().size()!=0) {
                entries.add(en);
            }
        }
        EntryData entry = entries.iterator().next();

        if (entries.size()==0) {
            String id="[none]";
            GroupData group = app.db().groups().iterator().next();
            entry.inGroup(group);

            app.goTo().homePage();
            app.entry().addContactToGroup(entry, group, id);
        }

        GroupData group = entry.getGroups().iterator().next();
        app.goTo().homePage();
        //app.entry().selectGroupById(entry);
        app.entry().selectGroupById("" + group.getId());
        app.entry().selectEntryById(entry.getId());
        app.entry().removeFromGroup();
        app.entry().goToGroupPage(group.getName());
       // app.entry().goToGroupPage(entry);
    }
}
