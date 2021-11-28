package ru.stqa.jft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.List;

public class EntryDeletionTests extends TestBase {

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
  public void testEntryDeletion() throws Exception {
    List<EntryData> before = app.entry().list();
    app.entry().selectEntry(before.size()-1);
    app.entry().deleteSelectedEntries();
    app.entry().acceptEntryDeleting();
    app.goTo().homePage();
    List<EntryData> after = app.entry().list();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove((before.size()-1));
    Assert.assertEquals(before, after);
  }

}
