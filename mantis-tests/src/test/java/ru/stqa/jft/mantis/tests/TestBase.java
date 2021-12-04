package ru.stqa.jft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.mantis.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    //    app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_defaults_inc.bak");
    }

    @AfterSuite(alwaysRun = true)
      public void tearDown() throws Exception {
     //   app.ftp().restore("config_defaults_inc.bak","config_defaults_inc.php");
        app.stop();
    }


}
