package ru.stqa.jft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.jft.mantis.model.MailMessage;
import ru.stqa.jft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String password = "password";
        List<Users> users = app.db().users();
        Users user = users.iterator().next();
        app.changePassword().start(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user.getName(), password));

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
