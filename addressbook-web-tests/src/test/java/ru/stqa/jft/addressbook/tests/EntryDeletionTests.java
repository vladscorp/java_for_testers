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
      app.entry().create(new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
              .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
              .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name"), true);
    }
  }

  @Test(enabled = true)
  public void testEntryDeletion() throws Exception {
    List<EntryData> before = app.entry().list();
    int index = before.size()-1;
    app.entry().delete(index);
    app.goTo().homePage();
    List<EntryData> after = app.entry().list();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
