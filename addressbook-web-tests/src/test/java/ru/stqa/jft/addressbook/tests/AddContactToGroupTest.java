package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups includedGrBefore = entry.getGroups();
        //проверка на привязку контакта сразу ко всем группам
        if (groups.size()==includedGrBefore.size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testGroup"));

            assertThat(app.db().groups().size(), equalTo(groups.size()+1));
            groups = app.db().groups();
        }
        // проверка на присутствие в выбранной группе
        for (GroupData g : groups) {
            int i=0;
            for (GroupData e : includedGrBefore) {
                if (g.equals(e)) {
                    break;
                } else i++;
            }
            if (i == includedGrBefore.size()) {
                group = g;
                break;
            }
        }
        //пришлось зайти в отображение по группам, т.к. в списке All после привязки открывается пустая страница
        String id;
        if (includedGrBefore.size()==0) {
            id="[none]";
        } else {
            id = ("" + includedGrBefore.iterator().next().getId());
        }
        entry.inGroup(group);
        app.goTo().homePage();
        app.entry().addContactToGroup(entry, group, id);

        assertEquals(includedGrBefore.size(), entry.getGroups().size()-1);
        assertThat(app.db().entriesById(entry.getId()).getGroups(), equalTo(includedGrBefore.withAdded(group)));

        Entries afterEn = app.db().entries();
        assertEquals(afterEn.size(), before.size());
        assertThat(afterEn, equalTo(before));

        Groups afterGr = app.db().groups();
        assertEquals(afterGr.size(), groups.size());
        assertThat(afterGr, equalTo(groups));
    }


}
