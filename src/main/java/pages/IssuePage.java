package pages;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Util;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssuePage extends Base {

    @FindBy(xpath = "//*[@id='key-val']")
    WebElement issueID;

    @FindBy(xpath = "//*[@id='summary-val']")
    WebElement issueSummaryHeader;

    @FindBy(xpath = "//*[@id='opsbar-operations_more']")
    WebElement moreOptionsDropdown;

    @FindBy(xpath = "//*[@id='create-subtask']/a")
    WebElement createSubtaskOption;

    @FindBy(xpath = "//*[@id='delete-issue']/a")
    WebElement deleteOption;

    @FindBy(xpath = "//*[@id='delete-issue-submit']")
    WebElement confirmDelete;

    @FindBy(xpath = "//*[@class='stsummary']/a")
    WebElement subtaskLink;

    @FindBy(xpath = "//*[@id='edit-issue']")
    WebElement editButton;


    public IssuePage() throws MalformedURLException {
    }


    public String getIssueId() {
        return Util.lookUpWebElementWithWait(issueID).getText();
    }

    public WebElement getIssueSummaryHeader() {
        return Util.lookUpWebElementWithWait(issueSummaryHeader);
    }

    public WebElement getMoreOptionsDropdown() {
        return moreOptionsDropdown;
    }

    public WebElement getCreateSubtaskOption() {
        return createSubtaskOption;
    }

    public void validateIssueBase(String issueSummary) {
        WebElement issueSummaryTheSame = Util.lookUpWebElementWithWait(issueSummaryHeader);
        assertEquals(issueSummary, issueSummaryTheSame.getText());
    }

    public void deleteIssue() {
        Actions actions = new Actions(driver);
        WebElement moreButton = Util.lookUpWebElementWithWait(moreOptionsDropdown);
        actions.moveToElement(moreButton).click().build().perform();
        WebElement deleteButton = Util.lookUpWebElementWithWait(deleteOption);
        deleteButton.click();
        WebElement confirmDeleteButton = Util.lookUpWebElementWithWait(confirmDelete);
        confirmDeleteButton.click();
    }

    public void validateSubtask(String subtaskSummary){
        WebElement subtask = Util.lookUpWebElementWithWait(subtaskLink);
        assertEquals(subtaskSummary, subtask.getText());
    }

    public void checkIfEditButtonIsDisplayed(String url){
        Util.navigateToUrl(url);
        WebElement editOption = Util.lookUpWebElementWithWait(editButton);
        assertTrue(editOption.isDisplayed());
    }

    public void clickOnEditButton() {
        Util.lookUpWebElementWithWait(editButton).click();
    }
}
