import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;
import util.Util;
import util.WebDriverUtil;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CreateIssueTests {

    LogInPage logInPage;
    DashboardPage dashboardPage;
    CreateIssuePage createIssuePage;
    IssuePage issuePage;
    CreateSubtaskPage createSubtaskPage;


    @BeforeEach
    public void setup() throws MalformedURLException {
        //Util.setUpRemoteWebDriver();
        logInPage = new LogInPage();
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        dashboardPage = new DashboardPage();
        createIssuePage = new CreateIssuePage();
        issuePage = new IssuePage();
        createSubtaskPage = new CreateSubtaskPage();
    }

    @AfterEach
    public void quit() {
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
        WebDriverUtil.quitRemoteWebDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/IssueTestData.csv", numLinesToSkip = 1)
    public void createIssuesTest(String projectName, String issueType, String issueSummary) throws Exception {
        try {
            createIssuePage.createIssueBase(projectName, issueType, issueSummary);
            createIssuePage.clickOnCreatedIssueModalLink();
            issuePage.validateIssueBase(issueSummary);
            issuePage.deleteIssue();
        } catch (Exception error) {
            Util.forceQuit();
            throw new Exception("No such element: " + projectName, error);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SubTaskTestData.csv", numLinesToSkip = 1)
    public void createIssueSubtaskTest(String projectName, String issueType, String issueSummary, String subTask) throws Exception {
        try {
            createIssuePage.createIssueBase(projectName, issueType, issueSummary);
            createIssuePage.clickOnCreatedIssueModalLink();
            createSubtaskPage.createSubtask(subTask);
            issuePage.validateSubtask(subTask);
            issuePage.deleteIssue();
        } catch (Exception error) {
            Util.forceQuit();
            throw new Exception("No such element: " + projectName, error);
        }
    }

    @Test
    public void cancelIssueTest(){
        String summaryFieldCheck = createIssuePage.cancelIssue("Main Testing Project (MTP)", "Bug", "Cancel Issue");

        //Validate that no new issue is created
        assertNotEquals(summaryFieldCheck, "Cancel Issue");
    }
}
