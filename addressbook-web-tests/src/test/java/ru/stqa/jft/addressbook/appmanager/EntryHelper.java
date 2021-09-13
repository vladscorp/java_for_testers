package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class EntryHelper extends HelperBase {

    private boolean acceptNextAlert = true;

    public EntryHelper(WebDriver wd) {
        super(wd);
    }

    public void acceptEntryDeleting() {
        acceptNextAlert = true;
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void deleteSelectedEntries() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectEntry() {
        click(By.id("9"));
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
