package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.PageFactory;
import selenide.helpers.CredentialsGenerator;
import selenide.helpers.User;
import selenide.pages.LoginPage;
import selenide.pages.MainPage;
import selenide.pages.RegistrationPage;
import selenide.components.Alerts;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationTests extends BaseTest {

    private MainPage mainPage = PageFactory.mainPage();
    private LoginPage loginPage = PageFactory.loginPage();
    private RegistrationPage registrationPage = PageFactory.registrationPage();

    private User newUser;
    private final User DEFAULT_USER = new User("TestNameUser", "TestPass");
    private String randomUsername = CredentialsGenerator.generateUsername(8);
    private String RandomPassword = CredentialsGenerator.generatePassword(10);

    @Test
    @Severity(CRITICAL)
    public void shouldRegisterClientAndAuthorize() {
        newUser = new User(randomUsername, RandomPassword);

        registrationPage.get().registration(newUser, Alerts.SUCCESSFUL_SIGN);
        loginPage.get().login(newUser);

        mainPage.shouldShowWelcome(newUser.getUsername());
        assertNotNull(loginPage.getCookie("tokenp_"));
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithExistedCreds() {

        registrationPage.get().registration(DEFAULT_USER, Alerts.USER_ALREADY_EXIST);
        registrationPage.getModal().shouldNotBe(hidden);

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithEmptyCreds() {
        newUser = new User("", "");

        registrationPage.get().registration(newUser, Alerts.EMPTY_FIELDS);
        registrationPage.getModal().shouldNotBe(hidden);

    }

}
