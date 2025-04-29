package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.PageManager;
import selenide.components.NavBarComponent;
import selenide.helpers.*;

import selenide.helpers.Alerts;
import selenide.valueObject.User;

import static com.codeborne.selenide.Condition.*;
import static config.ConfigProvider.CONFIG;
import static io.qameta.allure.SeverityLevel.*;

@Nested
@DisplayName("Login logic tests")
@Tag("regress")
public class LoginTests extends BaseTest {

    private final String DEFAULT_LOGIN = CONFIG.username();
    private final String DEFAULT_PASS = CONFIG.password();
    private final User DEFAULT_USER = new User(DEFAULT_LOGIN, DEFAULT_PASS);
    private String randomUsername = CredentialsGenerator.generateUsername(5);
    private String RandomPassword = CredentialsGenerator.generatePassword(8);
    private User newUser;
    private NavBarComponent navBarComponent = new NavBarComponent();

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful login")
    void successfulLogin() {
        PageManager.loginPage().get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.usernameAfterLogin().shouldBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with invalid data")
    public void errorAfterLoginInvalidCreds() {

        newUser = new User(randomUsername, RandomPassword);
        PageManager.loginPage().get().wrongLogin(newUser, Alerts.USER_NOT_EXIST);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with empty data")
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");
        PageManager.loginPage().get().wrongLogin(newUser, Alerts.EMPTY_FIELDS);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful logout")
    public void logout() {
        PageManager.loginPage().get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.goTo(navBarComponent.logout()).usernameAfterLogin().shouldBe(hidden);
    }
}
