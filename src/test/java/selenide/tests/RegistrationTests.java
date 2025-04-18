package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.PageManager;
import selenide.components.NavBarComponent;
import selenide.helpers.CredentialsGenerator;
import selenide.valueObject.User;
import selenide.pages.LoginPage;
import selenide.pages.RegistrationPage;
import selenide.components.Alerts;

import static com.codeborne.selenide.Condition.*;
import static config.ConfigProvider.CONFIG;
import static io.qameta.allure.SeverityLevel.*;

public class RegistrationTests extends BaseTest {

    private LoginPage loginPage = PageManager.loginPage();
    private RegistrationPage registrationPage = PageManager.registrationPage();
    private NavBarComponent navBarComponent = new NavBarComponent();

    private User newUser;
    private final String DEFAULT_LOGIN = CONFIG.username();
    private final String DEFAULT_PASS = CONFIG.password();
    private final User DEFAULT_USER = new User(DEFAULT_LOGIN, DEFAULT_PASS);
    private String randomUsername = CredentialsGenerator.generateUsername(8);
    private String RandomPassword = CredentialsGenerator.generatePassword(10);

    @Test
    @Severity(CRITICAL)
    public void shouldRegisterClientAndAuthorize() {
        newUser = new User(randomUsername, RandomPassword);
        System.out.println(randomUsername);
        System.out.println(RandomPassword);

        registrationPage.get().registration(newUser, Alerts.SUCCESSFUL_SIGN);
        loginPage.get().login(newUser);

        navBarComponent.shouldShowWelcome(newUser.getUsername());
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithExistedCreds() {
        System.out.println(DEFAULT_LOGIN + DEFAULT_PASS);
        registrationPage.get().registration(DEFAULT_USER, Alerts.USER_ALREADY_EXIST).getModal().shouldNotBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithEmptyCreds() {
        newUser = new User("", "");

        registrationPage.get().registration(newUser, Alerts.EMPTY_FIELDS).getModal().shouldNotBe(hidden);

    }

}
