package ru.stqa.jft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.jft.mantis.model.Users;

import java.io.IOException;

public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void start(Users user) {
        wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
        type(By.id("username"), "administrator");
        click(By.cssSelector("input[type=\"submit\"]"));
        type(By.id("password"), "root");
        click(By.cssSelector("input[type=\"submit\"]"));
        wd.get(app.getProperty("web.baseUrl")+"/manage_user_page.php");
        click(By.cssSelector("a[href=\"manage_user_edit_page.php?user_id=" + user.getId() + "\"]"));
        click(By.cssSelector("input[value=\"Сбросить пароль\"]"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
