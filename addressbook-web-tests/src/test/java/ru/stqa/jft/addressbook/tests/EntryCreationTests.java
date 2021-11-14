package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

public class EntryCreationTests extends TestBase {

  @Test
  public void testEntryCreation() throws Exception {
    app.getEntryHelper().gotoEntryPage();
    app.getEntryHelper().fillEntryForm(new EntryData("Ivan", "Aleksandrovich", "Petrov", "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345", "123156496879", "wqer@qwe.ru", "16", "September", "1980", "name"), true);
    app.getEntryHelper().submitEntryCreation();
    app.getEntryHelper().returnToHomePage();
  }


}
