package ru.stqa.jft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Comparator;
import java.util.List;

public class EntryCreationTests extends TestBase {

  @Test
  public void testEntryCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<EntryData> before = app.getEntryHelper().getEntryList();
    EntryData entry = new EntryData("Ivan", "Aleksandrovich", "Petrov", "vanko",
            "title", "comp", "блаблабла очень длинный адрес 23", "123345", "123156496879",
            "wqer@qwe.ru", "16", "September", "1980", "name");
    app.getEntryHelper().createEntry(entry, true);
    List<EntryData> after = app.getEntryHelper().getEntryList();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(entry);

    Comparator<? super EntryData> byId = (e1, e2) -> Integer.compare(e1.getId(), e2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
