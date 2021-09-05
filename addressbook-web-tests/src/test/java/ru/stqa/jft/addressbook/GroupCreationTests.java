package ru.stqa.jft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
      gotoGroupPage();
      initGroupCreation();
      fillGroupForm(new GroupData("name", "header", "footer"));
      submitGroupCreation();
      returnToGroupPage();
    }

}
