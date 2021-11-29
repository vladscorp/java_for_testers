package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntryAddressTests extends TestBase{

    @Test
    public void testEntryAddress() {
        app.goTo().homePage();
        EntryData entry = app.entry().all().iterator().next();
        EntryData entryInfoFromEditForm = app.entry().infoFromEditForm(entry);

        assertThat(entry.getAddress(), equalTo(entryInfoFromEditForm.getAddress()));

    }
}
