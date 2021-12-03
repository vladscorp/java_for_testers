package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntryInfoTests extends TestBase{

    @Test
    public void testEntryInfo() {
        app.goTo().homePage();
        EntryData entry = app.entry().all().iterator().next();
        EntryData entryInfoFromEditForm = app.entry().infoFromEditForm(entry);

        assertThat(entry.getAddress(), equalTo(entryInfoFromEditForm.getAddress()));
        assertThat(entry.getAllEmails(), equalTo(mergeEmails(entryInfoFromEditForm)));
        assertThat(entry.getAllPhones(), equalTo(mergePhones(entryInfoFromEditForm)));

    }


    private String mergeEmails(EntryData entry) {
        return Arrays.asList(entry.getEmail(), entry.getEmail2(), entry.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    private String mergePhones(EntryData entry) {
        return Arrays.asList(entry.getHome(), entry.getMobile(), entry.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(EntryInfoTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
