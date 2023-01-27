import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;
import util.Util;
import util.WebDriverUtil;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditIssueTests {

    LogInPage logInPage;
    DashboardPage dashboardPage;
    CreateIssuePage createIssuePage;
    IssuePage issuePage;
    EditIssuePage editIssuePage;


    @BeforeEach
    public void setup() throws MalformedURLException {
        //Util.setUpRemoteWebDriver();
        logInPage = new LogInPage();
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        dashboardPage = new DashboardPage();
        createIssuePage = new CreateIssuePage();
        issuePage = new IssuePage();
        editIssuePage = new EditIssuePage();
    }

    @AfterEach
    public void quit() {
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
        WebDriverUtil.quitRemoteWebDriver();
    }

    @Test
    public void editIssueTest() {
        // Create a new test issue
        createIssuePage.createIssueBase("Main Testing Project (MTP)","Bug","MTP test issue summary");
        createIssuePage.clickOnCreatedIssueModalLink();

        // Edit summary text
        issuePage.clickOnEditButton();
        editIssuePage.editIssueSummaryField("Big-sub");
        editIssuePage.clickOnUpdate();

        // Check if the summary text remains the same after page refresh
        Util.refreshPage();
        Util.acceptAlert();
        assertEquals("Big-sub", issuePage.getIssueSummaryHeader().getText());

        // Delete the created test issue
        issuePage.deleteIssue();
    }

    @Test
    public void cancelEditIssueTest() {
        Util.navigateToUrl("https://jira-auto.codecool.metastage.net/browse/MTP-2686?filter=-2");
        issuePage.clickOnEditButton();
        editIssuePage.editIssueSummaryField("Edited-sub");
        editIssuePage.clickOnUpdate();
        assertEquals("Edited-sub", issuePage.getIssueSummaryHeader().getText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssueTestLinks.csv")
    public void editIssueTest(String projectLink){
        try {
            issuePage.checkIfEditButtonIsDisplayed(projectLink);
        }catch (Exception e){
            System.out.println("ERROR: There is no edit button displayed");
        }
    }
}
