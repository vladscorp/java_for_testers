package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;

public class EntryModificationTests  extends TestBase {

    @Test(enabled = false)
    public void testEntryModification() {
        app.goTo().gotoHomePage();
        if (! app.getEntryHelper().thereAnEntry()) {
            app.getEntryHelper().createEntry(new EntryData("Ivan", "Aleksandrovich", "Petrov",
                    "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345",
                    "123156496879", "wqer@qwe.ru", "16", "September", "1980", "name"), true);
        }
        List<EntryData> before = app.getEntryHelper().getEntryList();
        app.getEntryHelper().initEntryModification(before.size()-1);
        EntryData entry = new EntryData("Ivan", "Aleksandrovich", "Petrov",
                "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345",
                "123156496879", "wqer@qwe.ru", "16", "September", "1980", null);
        app.getEntryHelper().fillEntryForm(entry, false);
        app.getEntryHelper().submitEntryModification();
        app.getEntryHelper().returnToHomePage();
        List<EntryData> after = app.getEntryHelper().getEntryList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(entry);

        Comparator<? super EntryData> byId = (e1, e2) -> Integer.compare(e1.getId(), e2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
