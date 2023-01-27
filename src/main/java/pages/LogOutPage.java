package pages;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Util;

import java.net.MalformedURLException;

public class LogOutPage extends Base {
    @FindBy(xpath = "//*[@class='title']/child::*[1]")
    WebElement logOutMessage;


    public LogOutPage() throws MalformedURLException {
    }

    public String getLogOutMessage() {
        return Util.lookUpWebElementWithWait(logOutMessage).getText();
    }
}
