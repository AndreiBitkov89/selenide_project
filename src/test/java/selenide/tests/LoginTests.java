package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.PageManager;
import selenide.components.NavBarComponent;
import selenide.helpers.*;
import selenide.pages.*;

import selenide.components.Alerts;
import selenide.valueObject.User;

import static com.codeborne.selenide.Condition.*;
import static config.ConfigProvider.CONFIG;
import static io.qameta.allure.SeverityLevel.*;

public class LoginTests extends BaseTest {

    private final String DEFAULT_LOGIN = CONFIG.username();
    private final String DEFAULT_PASS = CONFIG.password();
    private final User DEFAULT_USER = new User(DEFAULT_LOGIN, DEFAULT_PASS);
    private String randomUsername = CredentialsGenerator.generateUsername(5);
    private String RandomPassword = CredentialsGenerator.generatePassword(8);
    private User newUser;
    private NavBarComponent navBarComponent = new NavBarComponent();

    private LoginPage loginPage = PageManager.loginPage();


    @Test
    @Severity(CRITICAL)
    void successfulLogin() {
        loginPage.get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.usernameAfterLogin().shouldBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginInvalidCreds() {

        newUser = new User(randomUsername, RandomPassword);
        loginPage.get().wrongLogin(newUser, Alerts.USER_NOT_EXIST);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");
        loginPage.get().wrongLogin(newUser, Alerts.EMPTY_FIELDS);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    public void logout() {
        loginPage.get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.goTo(navBarComponent.logout()).usernameAfterLogin().shouldBe(hidden);
    }
}
