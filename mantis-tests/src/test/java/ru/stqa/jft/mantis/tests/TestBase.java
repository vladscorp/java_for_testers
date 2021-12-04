package ru.stqa.jft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.json.TypeToken;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.jft.mantis.appmanager.ApplicationManager;
import ru.stqa.jft.mantis.model.IssueRest;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

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

    public void skipIfNotFixedSoap(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpenSoap(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpenSoap(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        int id = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getStatus().getId().intValue();
        if (id != 80 && id != 90) {
            return true;
        } else return false;
    }

    public void skipIfNotFixedRest(int issueId) throws IOException {
        if (isIssueOpenRest(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpenRest(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<IssueRest> issueSet = new Gson().fromJson(issues, new TypeToken<Set<IssueRest>>(){}.getType());
        int state = issueSet.iterator().next().getState();
        if (state != 2 && state != 3) {
            return true;
        } else return false;
    }

    public static Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }


}
