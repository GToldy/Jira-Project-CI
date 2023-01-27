package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverUtil {

    private static WebDriverUtil webDriverUtil = null;
    private static RemoteWebDriver remoteWebDriver;
    private static WebDriver webDriver;

    private WebDriverUtil() {}


    public static void getInstance() {
        if (webDriverUtil == null) {
            webDriverUtil = new WebDriverUtil();
        }
    }



    private static void initWebDriver(String driverType) {
        if (webDriver == null) {
            switch (driverType) {
                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    break;
                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
                case "Safari":
                    WebDriverManager.safaridriver().setup();
                    webDriver = new SafariDriver();
                    break;
            }
        }

        assert webDriver != null;
        webDriver.manage().window().maximize();
    }

    private static void initRemoteWebDriver(String nodeUrl, String browserType)
            throws MalformedURLException {
        if (remoteWebDriver == null){
            switch (browserType.toLowerCase()){
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    remoteWebDriver = new RemoteWebDriver(new URL(nodeUrl), chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    remoteWebDriver = new RemoteWebDriver(new URL(nodeUrl), firefoxOptions);
                    break;
            }
        }
        assert remoteWebDriver != null;
        remoteWebDriver.manage().window().maximize();
    }


    public static WebDriver getRemoteWebDriver() throws MalformedURLException {
        String gridURL = System.getProperty("gridUrl", "@seleniumhub.codecool.metastage.net/wd/hub");
        String accessKey = System.getProperty("seleniumGridAccessKey", "CCAutoTest19.");
        String username = System.getProperty("seleniumGridUsername", "selenium");
        String nodeURL = "https://" + username + ":" + accessKey + gridURL;
        String browserType = System.getProperty("browserType", "chrome");
        initRemoteWebDriver(nodeURL, browserType);
        return remoteWebDriver;
    }

    public WebDriver getWebDriver(String driverType) {
        initWebDriver(driverType);
        return webDriver;
    }


    public static void quitWebDriver(){
        webDriver.quit();
        webDriver = null;
    }


    public static void quitRemoteWebDriver(){
        remoteWebDriver.quit();
        remoteWebDriver = null;
    }
}
