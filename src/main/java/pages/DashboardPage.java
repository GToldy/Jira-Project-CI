package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class DashboardPage extends Base {

    @FindBy(xpath = "//*[@id='header-details-user-fullname']")
    WebElement userProfile;

    @FindBy(xpath = "//*[@id='log_out']")
    WebElement logOut;

    @FindBy(xpath = "//*[@id='find_link']")
    WebElement issuesOption;

    @FindBy(xpath = "//*[@id='filter_lnk_reported_lnk']")
    WebElement reportedByMe;

    @FindBy(xpath = "//*[@id='create_link']")
    WebElement createButton;



    public DashboardPage() throws MalformedURLException {
    }


    public WebElement getUserProfileElement() {
        return userProfile;
    }

    public WebElement getLogOut() {
        return logOut;
    }

    public WebElement getIssuesOption() {
        return issuesOption;
    }

    public WebElement getReportedByMe() {
        return reportedByMe;
    }

    public String checkUsername() {
        return Util.lookUpWebElementWithWait(userProfile).getAttribute("data-username");
    }

    public void clickOnCreateButton() {
        Util.lookUpWebElementWithWait(createButton).click();
    }
}
