package pages;

import base.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class CreateIssuePage extends Base {

    DashboardPage dashboardPage;
    IssuePage issuePage;


    @FindBy(xpath = "//*[@id='project-field']")
    WebElement projectField;

    @FindBy(xpath = "//*[@id='issuetype-field']")
    WebElement issueTypeField;

    @FindBy(xpath = "//*[@id='summary']")
    WebElement summaryField;

    @FindBy(xpath = "//*[@id='create-issue-submit']")
    WebElement createIssueSubmitButton;

    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    WebElement createdIssueLink;

    @FindBy(xpath = "//*[@class='aui-button aui-button-link cancel']")
    WebElement cancelButton;

    public CreateIssuePage() throws MalformedURLException {
        dashboardPage = new DashboardPage();
        issuePage = new IssuePage();
    }

    private void setProjectField(String projectName) {
        WebElement projectInput = Util.lookUpWebElementWithWait(projectField);
        projectInput.click();
        projectInput.sendKeys(projectName + Keys.ENTER);
    }

    private void setIssueTypeField(String issueType) {
        WebElement issueTypeInput = Util.lookUpWebElementWithWait(issueTypeField);
        issueTypeInput.click();
        issueTypeInput.sendKeys(issueType + Keys.ENTER);
    }

    private void setSummaryField(String issueSummary) {
        WebElement summaryInput = Util.lookUpWebElementWithWait(summaryField);
        summaryInput.click();
        summaryInput.sendKeys(issueSummary);
    }

    private void clickOnSubmitIssue() {
        Util.lookUpWebElementWithWait(createIssueSubmitButton).click();
    }

    public void createIssueBase(String projectName, String issueType, String issueSummary) {
        dashboardPage.clickOnCreateButton();
        setProjectField(projectName);
        setIssueTypeField(issueType);
        setSummaryField(issueSummary);
        clickOnSubmitIssue();
    }

    public void clickOnCreatedIssueModalLink() {
        Util.lookUpWebElementWithWait(createdIssueLink).click();
    }

    public String cancelIssue(String projectName, String issueType, String issueSummary) {
        // Initiate new issue creation
        dashboardPage.clickOnCreateButton();
        this.setProjectField(projectName);
        this.setIssueTypeField(issueType);
        this.setSummaryField(issueSummary);

        // Find and click on cancel button
        Util.lookUpWebElementWithWait(cancelButton).click();

        // Handling Alert modal
        Util.acceptAlert();

        // Get issue header text to validate that no new issue is created
        Util.lookUpWebElementWithWait(dashboardPage.getIssuesOption()).click();
        Util.lookUpWebElementWithWait(dashboardPage.getReportedByMe()).click();
        return Util.lookUpWebElementWithWait(issuePage.getIssueSummaryHeader()).getText();
    }
}
