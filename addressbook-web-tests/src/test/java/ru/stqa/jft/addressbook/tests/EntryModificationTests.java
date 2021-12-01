package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EntryModificationTests  extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().entries().size() == 0) {
            EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                    .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                    .withWork("354332").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980");
            Groups groups = app.db().groups();
            entry.inGroup(groups.iterator().next());
            app.goTo().homePage();
            app.entry().create(entry, true);
        }
    }

    @Test(enabled = true)
    public void testEntryModification() {
        Entries before = app.db().entries();
        EntryData modifiedEntry = before.iterator().next();
        EntryData entry = new EntryData().withId(modifiedEntry.getId()).withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                .withWork("38486").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980");
        app.goTo().homePage();
        app.entry().modify(entry);
        Entries after = app.db().entries();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedEntry).withAdded(entry)));
        verifyEntryListInUI();
    }

}
