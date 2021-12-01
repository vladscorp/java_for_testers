package ru.stqa.jft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.jft.addressbook.model.Entries;
import ru.stqa.jft.addressbook.model.EntryData;
import ru.stqa.jft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class EntryHelper extends HelperBase {

    private boolean acceptNextAlert = true;

    public EntryHelper(WebDriver wd) {
        super(wd);
    }

    public void acceptEntryDeleting() {
        acceptNextAlert = true;
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void deleteSelectedEntries() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void selectEntry(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectEntryById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
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


    public void initEntryModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }


    public void returnToHomePage() {
        if (isElementPresent(By.linkText("home page"))) {
            click(By.linkText("home page"));
        } else {
            click(By.linkText("home"));
        }
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
        type(By.name("work"), entryData.getWork());
        type(By.name("email"), entryData.getEmail());
        select(By.name("bday"), entryData.getBday());
        select(By.name("bmonth"), entryData.getBmonth());
        type(By.name("byear"), entryData.getByear());
        //attach(By.name("photo"), entryData.getPhoto());
        if (creation) {
            if (entryData.getGroups().size() > 0) {
                Assert.assertTrue(entryData.getGroups().size() == 1);
                select(By.name("new_group"), entryData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initEntryCreation() {
        click(By.linkText("add new"));
    }

    public void submitEntryModification() {
        click(By.name("update"));
    }

    public void create(EntryData entry, boolean creation) {
        initEntryCreation();
        fillEntryForm(entry, creation);
        submitEntryCreation();
        returnToHomePage();
    }

    public void modify(EntryData entry) {
        initEntryModificationById(entry.getId());
        fillEntryForm(entry, false);
        submitEntryModification();
        returnToHomePage();
    }


    public void delete(EntryData entry) {
        selectEntryById(entry.getId());
        deleteSelectedEntries();
        acceptEntryDeleting();
    }


    public boolean thereAnEntry() {
        return isElementPresent(By.name("entry"));
    }


    public Entries all() {
        Entries entries = new Entries();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name=\"entry\"]"));
        for (WebElement element : elements) {
            List<WebElement> el = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = el.get(1).getText();
            String firstname = el.get(2).getText();
            String address = el.get(3).getText();
            String allEmails = el.get(4).getText();
            String allPhones = el.get(5).getText();
            EntryData entry = new EntryData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withAllPhones(allPhones).withAllEmails(allEmails);
            entries.add(entry);
        }
        return entries;
    }

    public EntryData infoFromEditForm(EntryData entry) {
        initEntryModificationById(entry.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new EntryData().withId(entry.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withHome(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
