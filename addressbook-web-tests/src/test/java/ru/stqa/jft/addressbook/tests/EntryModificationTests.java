package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;

public class EntryModificationTests  extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.entry().thereAnEntry()) {
            app.entry().create(new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                    .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                    .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name"), true);
        }
    }

    @Test(enabled = true)
    public void testEntryModification() {
        List<EntryData> before = app.entry().list();
        EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
                .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
                .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980");
        int index = before.size()-1;
        app.entry().modify(entry, index);
        List<EntryData> after = app.entry().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(entry);

        Comparator<? super EntryData> byId = (e1, e2) -> Integer.compare(e1.getId(), e2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
