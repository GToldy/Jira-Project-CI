package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;

import java.net.MalformedURLException;
import java.time.Duration;

public class Util {

    private static WebDriver driver;

    static {
        try {
            driver = WebDriverUtil.getRemoteWebDriver();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final long timeout = Long.parseLong(System.getProperty("timeout", "3000"));
    public static String correctUsername = System.getProperty("correctUsername");
    public static String correctPassword = System.getProperty("correctPassword");
    public static String seleniumGridUsername = System.getProperty("seleniumGridUsername");
    public static String usernameForCaptchaTest = System.getProperty("usernameForCaptchaTest");
    public static String incorrectUsername = System.getProperty("incorrectUsername");
    public static String incorrectPassword = System.getProperty("incorrectPassword");
    public static String baseUrl = System.getProperty("baseUrl", "https://jira-auto.codecool.metastage.net/login.jsp");


    // Constants
    public static final String loginErrorAlertMessage = "Sorry, your username and password are incorrect - please try again.";
    public static final String captchaErrorMessage = "Sorry, your userid is required to answer a CAPTCHA question correctly.";
    public static final String successfullyLoggedOutMessage = "You are now logged out. Any automatic login has also been stopped.";



    public static WebElement lookUpWebElementWithWait(WebElement element) {
        return new WebDriverWait(driver, Duration.ofMillis(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement lookUpWebElementWithWait(String selector) {
        return new WebDriverWait(driver, Duration.ofMillis(timeout)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
    }

    public static WebElement lookUpWebElementWithWait(By element) {
        return new WebDriverWait(driver, Duration.ofMillis(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement lookUpWebElementWithWait(int duration, String selector) {
        return new WebDriverWait(driver, Duration.ofMillis(duration)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
    }

    public static By lookUpWebElementByRelativeLocator(String parent, String child) {
        return RelativeLocator.with(By.cssSelector(parent)).below(By.cssSelector(child));
    }

    public static void waitForDashboard() throws MalformedURLException {
        DashboardPage dashboardPage = new DashboardPage();
        Util.lookUpWebElementWithWait(dashboardPage.getUserProfileElement());
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }


    public static void logOut(WebElement userProfile, WebElement logOut) {
        WebElement userDropdown = lookUpWebElementWithWait(userProfile);
        Util.scrollToElement(driver, userDropdown);
        userDropdown.click();
        WebElement logOutOption = lookUpWebElementWithWait(logOut);
        logOutOption.click();
    }

    public static void navigateToUrl(String url) {
        driver.get(url);
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }


    public static void setChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public static void getChromeDriver() {
        driver = new ChromeDriver();
    }

    public static void quitBrowser() {
        driver.quit();
    }

    public static void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void forceQuit() {
        quitBrowser();
        acceptAlert();
    }
}
