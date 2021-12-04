package ru.stqa.jft.mantis.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.jft.mantis.model.IssueRest;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

        @BeforeClass
        public void init() {
            RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        }

        @Test
        public void testRestCreateIssue() throws IOException {
            Set<IssueRest> oldIssues = getIssues();
            IssueRest newIssue = new IssueRest().withSubject("Test Vlad").withDescription("New test issue");
            int issueId = createIssue(newIssue);
            Set<IssueRest> newIssues = getIssues();
            oldIssues.add(newIssue.withId(issueId));
            assertEquals(newIssues, oldIssues);
        }


        private Set<IssueRest> getIssues() throws IOException {
            String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
            JsonElement parsed = new JsonParser().parse(json);
            JsonElement issues = parsed.getAsJsonObject().get("issues");
            return new Gson().fromJson(issues, new TypeToken<Set<IssueRest>>(){}.getType());
        }

        private int createIssue(IssueRest newIssue) throws IOException {
            String json = RestAssured.given()
                    .parameter("subject", newIssue.getSubject())
                    .parameter("description", newIssue.getDescription())
                    .post("https://bugify.stqa.ru/api/issues.json").asString();
            JsonElement parsed = new JsonParser().parse(json);
            return parsed.getAsJsonObject().get("issue_id").getAsInt();
        }

}
