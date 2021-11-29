package ru.stqa.jft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.EntryData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntryPhoneTests extends TestBase{

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        EntryData entry = app.entry().all().iterator().next();
        EntryData entryInfoFromEditForm = app.entry().infoFromEditForm(entry);

        assertThat(entry.getAllPhones(), equalTo(mergePhones(entryInfoFromEditForm)));
    }

    private String mergePhones(EntryData entry) {
        return Arrays.asList(entry.getHome(), entry.getMobile(), entry.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(EntryPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
