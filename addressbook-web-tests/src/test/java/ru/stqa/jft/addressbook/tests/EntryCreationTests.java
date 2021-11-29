package ru.stqa.jft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EntryCreationTests extends TestBase {

  @Test(enabled = true)
  public void testEntryCreation() throws Exception {
    app.goTo().homePage();
    Entries before = app.entry().all();
    EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
            .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
            .withWork("65464").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name");
    app.entry().create(entry, true);
    Set<EntryData> after = app.entry().all();
    assertEquals(after.size(), before.size()+1);

    assertThat(after, equalTo(
            before.withAdded(entry.withId(after.stream().mapToInt((e) -> e.getId()).max().getAsInt()))));
  }
}
