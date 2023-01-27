package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class CreateSubtaskPage extends Base {

    IssuePage issuePage;


    @FindBy(xpath = "//*[@id='summary']")
    WebElement summaryInput;

    @FindBy(xpath = "//*[@id='create-issue-submit']")
    WebElement createSubtaskSubmitButton;



    public CreateSubtaskPage() throws MalformedURLException {
        issuePage = new IssuePage();
    }




    public void createSubtask(String subtaskSummary){
        Actions actions = new Actions(driver);
        WebElement moreButton = Util.lookUpWebElementWithWait(issuePage.getMoreOptionsDropdown());
        actions.moveToElement(moreButton).click().build().perform();
        Util.lookUpWebElementWithWait(issuePage.getCreateSubtaskOption()).click();
        Util.lookUpWebElementWithWait(summaryInput).sendKeys(subtaskSummary);
        Util.lookUpWebElementWithWait(createSubtaskSubmitButton).click();
    }

}
