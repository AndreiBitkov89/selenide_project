package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.PageManager;
import selenide.components.*;
import selenide.helpers.*;
import selenide.valueObject.User;
import selenide.webpages.*;

import static com.codeborne.selenide.Condition.*;
import static config.ConfigProvider.*;
import static io.qameta.allure.SeverityLevel.*;

@Nested
@DisplayName("Registration tests")
@Tag("regress")
public class RegistrationTests extends BaseTest {

    private NavBarComponent navBarComponent = new NavBarComponent();

    private User newUser;
    private final String DEFAULT_LOGIN = CONFIG.username();
    private final String DEFAULT_PASS = CONFIG.password();
    private final User DEFAULT_USER = new User(DEFAULT_LOGIN, DEFAULT_PASS);
    private String randomUsername = CredentialsGenerator.generateUsername(8);
    private String RandomPassword = CredentialsGenerator.generatePassword(10);

    @Test
    @Severity(CRITICAL)
    @Tag("smoke")
    @DisplayName("Successful registration")
    public void shouldRegisterClientAndAuthorize() {
        newUser = new User(randomUsername, RandomPassword);
        System.out.println(randomUsername);
        System.out.println(RandomPassword);

        PageManager.registrationPage().get().registration(newUser, Alerts.SUCCESSFUL_SIGN);
        PageManager.loginPage().get().login(newUser);

        navBarComponent.shouldShowWelcome(newUser.getUsername());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with existed user's data")
    public void errorAfterRegWithExistedCreds() {
        System.out.println(DEFAULT_LOGIN + DEFAULT_PASS);
        PageManager.registrationPage().get().registration(DEFAULT_USER, Alerts.USER_ALREADY_EXIST).getModal().shouldNotBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with empty fields")
    public void errorAfterRegWithEmptyCreds() {
        newUser = new User("", "");

        PageManager.registrationPage().get().registration(newUser, Alerts.EMPTY_FIELDS).getModal().shouldNotBe(hidden);

    }
}
