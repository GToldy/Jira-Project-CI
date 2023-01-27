package pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Util;

import java.net.MalformedURLException;

public class LogInPage extends Base {

    @FindBy(xpath = "//input[@id='login-form-username']")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@id='login-form-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-form-submit']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@class='aui-message aui-message-error']/child::p")
    WebElement logInErrorMessage;

    @FindBy(xpath = "//*[@id='login-form-os-captcha']")
    WebElement captchaInput;

    @FindBy(xpath = "//img[@class='captcha-image']")
    WebElement captchaImage;




    public LogInPage() throws MalformedURLException {
    }




    private void setUsernameInput(String username) {
        Util.lookUpWebElementWithWait(usernameInput).sendKeys(username);
    }

    private void setPasswordInput(String password) {
        Util.lookUpWebElementWithWait(passwordInput).sendKeys(password);
    }

    private void clickOnLoginButton() {
        Util.lookUpWebElementWithWait(loginButton).click();
    }



    public void logInWithUser(String url, String username, String password) {
        Util.navigateToUrl(url);
        this.setUsernameInput(username);
        this.setPasswordInput(password);
        this.clickOnLoginButton();
    }

    public String getErrorMessage() {
        return Util.lookUpWebElementWithWait(logInErrorMessage).getText();
    }

    public WebElement getCaptchaInput() {
        return Util.lookUpWebElementWithWait(captchaInput);
    }

    public WebElement getCaptchaImage() {
        return Util.lookUpWebElementWithWait(captchaImage);
    }
}
