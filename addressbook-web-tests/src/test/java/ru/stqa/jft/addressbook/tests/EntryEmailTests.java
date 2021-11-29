package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntryEmailTests extends TestBase{

    @Test
    public void testEntryEmails() {
        app.goTo().homePage();
        EntryData entry = app.entry().all().iterator().next();
        EntryData entryInfoFromEditForm = app.entry().infoFromEditForm(entry);

        assertThat(entry.getAllEmails(), equalTo(mergeEmails(entryInfoFromEditForm)));

    }

    private String mergeEmails(EntryData entry) {
        return Arrays.asList(entry.getEmail(), entry.getEmail2(), entry.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
