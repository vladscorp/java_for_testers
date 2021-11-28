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
            app.entry().create(new EntryData("Ivan", "Aleksandrovich", "Petrov",
                    "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345",
                    "123156496879", "wqer@qwe.ru", "16", "September", "1980", "name"), true);
        }
    }

    @Test(enabled = true)
    public void testEntryModification() {
        List<EntryData> before = app.entry().list();
        app.entry().initEntryModification(before.size()-1);
        EntryData entry = new EntryData("Ivan", "Aleksandrovich", "Petrov",
                "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345",
                "123156496879", "wqer@qwe.ru", "16", "September", "1980", null);
        app.entry().fillEntryForm(entry, false);
        app.entry().submitEntryModification();
        app.entry().returnToHomePage();
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
