package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class EntryModificationTests  extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.entry().all().size() == 0) {
            app.entry().create(new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                    .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                    .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name"), true);
        }
    }

    @Test(enabled = true)
    public void testEntryModification() {
        Set<EntryData> before = app.entry().all();
        EntryData modifiedEntry = before.iterator().next();
        EntryData entry = new EntryData().withId(modifiedEntry.getId()).withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980");
        app.entry().modify(entry);
        Set<EntryData> after = app.entry().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedEntry);
        before.add(entry);
        Assert.assertEquals(before, after);
    }

}
