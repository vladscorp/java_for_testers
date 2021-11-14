package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.jft.addressbook.model.EntryData;

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

    public void initEntryModification() {
       // click(By.xpath("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img"));
        click(By.xpath("//img[@alt='Edit']"));
    }


    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitEntryCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillEntryForm(EntryData entryData, boolean creation) {
        type(By.name("firstname"), entryData.getFirstname());
        type(By.name("middlename"), entryData.getMiddlename());
        type(By.name("lastname"), entryData.getLastname());
        type(By.name("nickname"), entryData.getNickname());
        type(By.name("title"), entryData.getTitle());
        type(By.name("company"), entryData.getCompany());
        type(By.name("address"), entryData.getAddress());
        type(By.name("home"), entryData.getHome());
        type(By.name("mobile"), entryData.getMobile());
        type(By.name("email"), entryData.getEmail());
        select(By.name("bday"), entryData.getBday());
        select(By.name("bmonth"), entryData.getBmonth());
        type(By.name("byear"), entryData.getByear());
        if (creation) {
            select(By.name("new_group"), entryData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoEntryPage() {
        click(By.linkText("add new"));
    }

    public void submitEntryModification() {
        click(By.name("update"));
    }
}
