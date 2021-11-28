package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;

public class EntryCreationTests extends TestBase {

  @Test(enabled = true)
  public void testEntryCreation() throws Exception {
    app.goTo().homePage();
    List<EntryData> before = app.entry().list();
    EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
            .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
            .withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name");
    app.entry().create(entry, true);
    List<EntryData> after = app.entry().list();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(entry);

    Comparator<? super EntryData> byId = (e1, e2) -> Integer.compare(e1.getId(), e2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
