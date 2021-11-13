package ru.stqa.jft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.jft.addressbook.appmanager.ApplicationManager;
import ru.stqa.jft.addressbook.model.EntryData;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
      public void tearDown() throws Exception {
        app.stop();
    }


}
