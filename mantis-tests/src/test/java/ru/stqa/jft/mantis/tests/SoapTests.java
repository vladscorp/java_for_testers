package ru.stqa.jft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.jft.mantis.model.IssueSoap;
import ru.stqa.jft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testSoapCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixedSoap(2);
        Set<Project> projects = app.soap().getProjects();
        IssueSoap issue = new IssueSoap().withSummary("Test2 issue")
                .withDescription("Test2 issue description").withProject(projects.iterator().next());
        IssueSoap created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
