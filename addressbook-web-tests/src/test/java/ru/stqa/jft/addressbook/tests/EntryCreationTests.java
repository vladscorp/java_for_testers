package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class EntryCreationTests extends TestBase {

  @Test(enabled = true)
  public void testEntryCreation() throws Exception {
    app.goTo().homePage();
    Set<EntryData> before = app.entry().all();
    EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
            .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
            .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name");
    app.entry().create(entry, true);
    Set<EntryData> after = app.entry().all();
    Assert.assertEquals(after.size(), before.size()+1);

    entry.withId(after.stream().mapToInt((e) -> e.getId()).max().getAsInt());
    before.add(entry);
    Assert.assertEquals(after, before);
  }
}
