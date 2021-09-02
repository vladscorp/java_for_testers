package ru.stqa.jft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class EntryCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testEntryCreation() throws Exception {
    gotoNewEntryPage();
    fillEntryForm(new EntryData("Ivan", "Aleksandrovich", "Petrov", "vanko", "title", "comp", "блаблабла очень длинный адрес 23", "123345", "123156496879", "wqer@qwe.ru", "16", "September", "1980"));
    submitEntryCreation();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitEntryCreation() {
    wd.findElement(By.xpath("//input[21]")).click();
  }

  private void fillEntryForm(EntryData entryData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(entryData.firstname());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(entryData.middlename());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(entryData.lastname());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(entryData.nickname());
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(entryData.title());
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(entryData.company());
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(entryData.address());
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(entryData.home());
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(entryData.mobile());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(entryData.email());
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(entryData.bday());
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(entryData.bmonth());
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(entryData.byear());
  }

  private void gotoNewEntryPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
