package pages;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Util;

import java.net.MalformedURLException;

public class ProjectSummaryPage extends Base {


    @FindBy(xpath = "//dd[@class='project-meta-value' and not(descendant::*)]")
    WebElement projectKey;

    @FindBy(xpath = "//*[@id='main']/*[contains(@class, 'page-heading')]")
    WebElement cannotViewProjectMessage;




    public ProjectSummaryPage() throws MalformedURLException {
    }




    public String getProjectKey() {
        return Util.lookUpWebElementWithWait(projectKey).getText();
    }

    public String getCannotViewProjectMessage() {
        return Util.lookUpWebElementWithWait(cannotViewProjectMessage).getText();
    }
}
