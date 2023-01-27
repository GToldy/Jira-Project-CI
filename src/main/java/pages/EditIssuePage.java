package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class EditIssuePage extends Base {
    @FindBy(xpath = "//*[@id='summary']")
    WebElement issueSummary;

    @FindBy(xpath = "//*[@id='edit-issue-submit']")
    WebElement updateButton;


    public EditIssuePage() throws MalformedURLException {
    }


    public void editIssueSummaryField(String newSummary) {
        WebElement summaryEditField = Util.lookUpWebElementWithWait(issueSummary);
        summaryEditField.click();
        summaryEditField.clear();
        summaryEditField.sendKeys(newSummary);
    }

    public void clickOnUpdate() {
        Util.lookUpWebElementWithWait(updateButton).click();
    }
}
