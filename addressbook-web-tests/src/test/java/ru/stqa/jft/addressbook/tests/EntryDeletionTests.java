package ru.stqa.jft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.List;

public class EntryDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testEntryDeletion() throws Exception {
    app.goTo().gotoHomePage();
    if (! app.getEntryHelper().thereAnEntry()) {
      app.getEntryHelper().createEntry(new EntryData("Ivan", "Aleksandrovich", "Petrov", "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345", "123156496879", "wqer@qwe.ru", "16", "September", "1980", "name"), true);
    }
    List<EntryData> before = app.getEntryHelper().getEntryList();
    app.getEntryHelper().selectEntry(before.size()-1);
    app.getEntryHelper().deleteSelectedEntries();
    app.getEntryHelper().acceptEntryDeleting();
    app.goTo().gotoHomePage();
    List<EntryData> after = app.getEntryHelper().getEntryList();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove((before.size()-1));
    Assert.assertEquals(before, after);
  }

}
