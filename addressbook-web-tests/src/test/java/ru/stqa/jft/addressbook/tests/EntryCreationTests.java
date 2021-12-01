package ru.stqa.jft.addressbook.tests;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EntryCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validEntriesFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<EntryData> entries = gson.fromJson(json, new TypeToken<List<EntryData>>(){}.getType()); //List<EntryData>.class
    return entries.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validEntriesFromJson")
  public void testEntryCreation(EntryData entry) throws Exception {
    app.goTo().homePage();
    Entries before = app.db().entries();
   /* File photo = new File("src/test/resources/doors.jpg");
    EntryData entry = new EntryData().withFirstname("Ivan").withMiddlename("Aleksandrovich").withLastname("Petrov").withNickname("vanko")
            .withTitle("title").withCompany("comp").withAddress("блаблабла очень длинный адрес 23").withHome("123345").withMobile("123156496879")
            .withWork("65464").withEmail("wqer@qwe.ru").withBday("16").withBmonth("September").withByear("1980").withGroup("name")
            .withPhoto(photo);*/
    app.entry().create(entry, true);
    Set<EntryData> after = app.db().entries();
    assertEquals(after.size(), before.size()+1);

    assertThat(after, equalTo(
            before.withAdded(entry.withId(after.stream().mapToInt((e) -> e.getId()).max().getAsInt()))));
  }

}
