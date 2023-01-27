import org.junit.jupiter.api.*;
import pages.DashboardPage;
import pages.LogInPage;
import pages.LogOutPage;
import util.Util;
import util.WebDriverUtil;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInTests {

    LogInPage logInPage;
    DashboardPage dashboardPage;
    LogOutPage logOutPage;


    private final String emptyUsername = "";
    private final String emptyPassword = "";




    @BeforeEach
    public void setup() throws MalformedURLException {
//        Util.setUpRemoteWebDriver();
        logInPage = new LogInPage();
        dashboardPage = new DashboardPage();
        logOutPage = new LogOutPage();
    }

    @AfterEach
    public void quit() {
        WebDriverUtil.quitRemoteWebDriver();
    }


    @Test
    public void testSuccessfulLogIn() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);


        String loggedInUsername = dashboardPage.checkUsername();
        assertEquals(Util.correctUsername, loggedInUsername);

        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
    }

    @Test
    public void testIncorrectUsername() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, Util.incorrectUsername, Util.correctPassword);

        String alertMessage = logInPage.getErrorMessage();

        assertEquals(Util.loginErrorAlertMessage, alertMessage);
    }

    @Test
    public void testIncorrectPassword() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.incorrectPassword);

        String alertMessage = logInPage.getErrorMessage();

        assertEquals(Util.loginErrorAlertMessage, alertMessage);

        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
    }

    @Test
    public void testEmptyFields() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, emptyUsername, emptyPassword);

        String alertMessage = logInPage.getErrorMessage();

        assertEquals(Util.loginErrorAlertMessage, alertMessage);
    }

    @Test
    public void testEmptyPassword() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, emptyPassword);

        String alertMessage = logInPage.getErrorMessage();

        assertEquals(Util.loginErrorAlertMessage, alertMessage);

        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);
        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());
    }

    @Test
    public void testLogInWithCaptcha() throws MalformedURLException {
        for (int i=0; i<3; i++) {
            Util.refreshPage();
            logInPage.logInWithUser(Util.baseUrl, Util.usernameForCaptchaTest, Util.incorrectPassword);
        }

        String alertMessage = logInPage.getErrorMessage();

        assertEquals(Util.captchaErrorMessage, alertMessage);

        assertTrue(logInPage.getCaptchaInput().isDisplayed());
        assertTrue(logInPage.getCaptchaImage().isDisplayed());
    }

    @Test
    public void testLogOut() throws MalformedURLException {
        logInPage.logInWithUser(Util.baseUrl, Util.correctUsername, Util.correctPassword);

        Util.logOut(dashboardPage.getUserProfileElement(), dashboardPage.getLogOut());

        assertEquals(Util.successfullyLoggedOutMessage, logOutPage.getLogOutMessage());
    }
}
