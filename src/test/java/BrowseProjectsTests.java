import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardPage;
import pages.LogInPage;
import pages.ProjectSummaryPage;
import util.Util;
import util.WebDriverUtil;


import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProjectsTests {

    LogInPage logInPage;
    DashboardPage dashboardPage;
    ProjectSummaryPage projectSummaryPage;


    private void checkProjectSummaryPage(String projectName) {
        Util.navigateToUrl("https://jira-auto.codecool.metastage.net/projects/" + projectName + "/summary");
        assertEquals(projectName, projectSummaryPage.getProjectKey());
    }




    @BeforeEach
    public void setup() throws MalformedURLException {
        //Util.setUpRemoteWebDriver();
        logInPage = new LogInPage();
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        dashboardPage = new DashboardPage();
        projectSummaryPage = new ProjectSummaryPage();
    }

    @AfterEach
    public void quit() {
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
        WebDriverUtil.quitRemoteWebDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseProjectTestData.csv")
    public void browseProjectTest(String projectName){
        checkProjectSummaryPage(projectName);
    }

    @Test
    public void browseNonExistingProjectTest(){
        Util.navigateToUrl("https://jira-auto.codecool.metastage.net/projects/DUCK/summary");
        String nonExistingProjectPage = projectSummaryPage.getCannotViewProjectMessage();
        assertEquals("You can't view this project", nonExistingProjectPage);
    }
}
