import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardPage;
import pages.IssuePage;
import pages.LogInPage;
import util.Util;
import util.WebDriverUtil;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BrowseIssueTests {

    LogInPage logInPage;
    DashboardPage dashboardPage;
    IssuePage issuePage;


    public String browseProjectIssues(String projectName, String issueNumber) {
        Util.navigateToUrl("https://jira-auto.codecool.metastage.net/projects/" + projectName + "/issues/"+projectName+"-"+issueNumber+"?filter=allissues");
        return issuePage.getIssueId();
    }




    @BeforeEach
    public void setup() throws MalformedURLException {
        //Util.setUpRemoteWebDriver();
        logInPage = new LogInPage();
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        dashboardPage = new DashboardPage();
        issuePage = new IssuePage();
    }

    @AfterEach
    public void quit() {
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
        WebDriverUtil.quitRemoteWebDriver();
    }

    @Test
    public void browseNonExistingIssueTest(){
        assertNotEquals("MTP-0", browseProjectIssues("MTP","0"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseIssuesTestData.csv", numLinesToSkip = 1)
    public void browseIssueTest(String expProjectName, String projectName, String issueNumber) throws IOException{
        try {
            assertEquals(expProjectName, browseProjectIssues(projectName,issueNumber));
        }catch (Exception e){
            System.out.println("ERROR: There is no such issue");
        }
    }
}
