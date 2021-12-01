package ru.stqa.jft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.GroupData;
import ru.stqa.jft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
      public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " +m.getName() + " with parameters " + Arrays.asList(p));

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " +m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyEntryListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Entries dbEntries = app.db().entries();
            Entries uiEntries = app.entry().all();
            assertThat(uiEntries, equalTo(dbEntries.stream()
                    .map((e) -> new EntryData().withId(e.getId()).withFirstname(e.getFirstname()).withLastname(e.getLastname())
                            .withAddress(e.getAddress()).withAllPhones(e.getHome() + e.getMobile() + e.getWork())
                            .withAllEmails(e.getEmail() + e.getEmail2() + e.getEmail3()))
                    .collect(Collectors.toSet())));
        }
    }


}
