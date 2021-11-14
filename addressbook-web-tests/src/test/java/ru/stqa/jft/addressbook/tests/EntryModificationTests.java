package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

public class EntryModificationTests  extends TestBase {

    @Test
    public void testEntryModification() {
        app.getEntryHelper().initEntryModification();
        app.getEntryHelper().fillEntryForm(new EntryData("Ivan", "Aleksandrovich", "Petrov", "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345", "123156496879", "wqer@qwe.ru", "16", "September", "1980", null), false);
        app.getEntryHelper().submitEntryModification();
        app.getEntryHelper().returnToHomePage();
    }
}
