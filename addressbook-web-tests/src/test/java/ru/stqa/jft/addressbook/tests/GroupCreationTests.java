package ru.stqa.jft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getGroupHelper().returnToGroupPage();
    }

}
