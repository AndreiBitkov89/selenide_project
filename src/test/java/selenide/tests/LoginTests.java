package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.PageFactory;
import selenide.helpers.CredentialsGenerator;
import selenide.helpers.User;
import selenide.pages.LoginPage;
import selenide.pages.MainPage;
import selenide.components.Alerts;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage = PageFactory.loginPage();
    private MainPage mainPage = PageFactory.mainPage();

    private final User DEFAULT_USER = new User("TestNameUser", "TestPass");
    private String randomUsername = CredentialsGenerator.generateUsername(8);
    private String RandomPassword = CredentialsGenerator.generatePassword(10);
    private User newUser;

    @Test
    @Severity(CRITICAL)
    void successfulLogin() {
        loginPage.get().login(DEFAULT_USER);
        loginPage.getModal().shouldBe(visible);

        assertNotNull(loginPage.getCookie("tokenp_"));
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginInvalidCreds() {

        newUser = new User(randomUsername, RandomPassword);
        loginPage.get().wrongLogin(newUser, Alerts.USER_NOT_EXIST);
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);
        assertNull(loginPage.getCookie("tokenp_"));
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");

        loginPage.get().wrongLogin(newUser, Alerts.EMPTY_FIELDS);
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);
        assertNull(loginPage.getCookie("tokenp_"));
    }

}
