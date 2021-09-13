package ru.stqa.jft.addressbook.tests;


import org.testng.annotations.Test;

public class EntryDeletionTests extends TestBase {

  @Test
  public void testEntryDeletion() throws Exception {
    app.getEntryHelper().selectEntry();
    app.getEntryHelper().deleteSelectedEntries();
    app.getEntryHelper().acceptEntryDeleting();
  }

}
