package ru.stqa.jft.addressbook.tests;


import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EntryDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().entries().size() == 0) {
      app.goTo().homePage();
      app.entry().create(new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
              .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
              .withWork("554665").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name"), true);
    }
  }

  @Test(enabled = true)
  public void testEntryDeletion() throws Exception {
    Entries before = app.db().entries();
    EntryData deletedEntry = before.iterator().next();
    app.goTo().homePage();
    app.entry().delete(deletedEntry);
    app.goTo().homePage();
    Entries after = app.db().entries();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedEntry)));

  }


}
