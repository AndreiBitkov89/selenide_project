package selenide_tests;

import constants.AlertTypes;
import pages.commonComponents.NavBarComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import valueObjects.User;
import valueObjects.UserRegistry;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;

@DisplayName("Registration tests")
public class RegistrationTests extends BaseTest {

    private NavBarComponent navBarComponent;
    private User newUser;
    private final User DEFAULT_USER = UserRegistry.get("default");


    @BeforeEach
    void setUpPage() {
        UserRegistry.createRandomUser("new");
        navBarComponent = new NavBarComponent();
    }


    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful registration")
    @Tag("regress")
    @Tag("smoke")
    public void shouldRegisterClientAndAuthorize() {
        newUser = UserRegistry.get("new");
        PageManager.registrationPage().get().registration(newUser, AlertTypes.SUCCESSFUL_SIGN);
        PageManager.loginPage().get().login(newUser);

        navBarComponent.shouldShowWelcome(newUser.getUsername());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with existed user's data")
    @Tag("regress")
    public void errorAfterRegWithExistedCreds() {
        PageManager.registrationPage().get().registration(DEFAULT_USER, AlertTypes.USER_ALREADY_EXIST).getModal().shouldNotBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with empty fields")
    @Tag("regress")
    public void errorAfterRegWithEmptyCreds() {
        newUser = new User("", "");

        PageManager.registrationPage().get().registration(newUser, AlertTypes.EMPTY_FIELDS).getModal().shouldNotBe(hidden);

    }
}
