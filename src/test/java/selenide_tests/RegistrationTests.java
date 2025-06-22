package selenide_tests;

import enums.AlertType;
import pages.commonComponents.NavBarComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import valueObjects.User;
import valueObjects.UserRegistry;

import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        navBarComponent.clickSignUp();
        PageManager.registrationPage().get().registration(newUser, AlertType.SUCCESSFUL_SIGN);
        navBarComponent.clickLogin();
        PageManager.loginPage().get().login(newUser);

        assertTrue(navBarComponent.shouldShowUserName(true), "User name is shown");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with existed user's data")
    @Tag("regress")
    public void errorAfterRegWithExistedCreds() {
        navBarComponent.clickSignUp();
        PageManager.registrationPage().get().registration(DEFAULT_USER, AlertType.USER_ALREADY_EXIST);
        assertTrue(PageManager.registrationPage().isModalDisplayed(true), "Modal is hidden");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after registration with empty fields")
    @Tag("regress")
    public void errorAfterRegWithEmptyCreds() {
        newUser = new User("", "");
        navBarComponent.clickSignUp();
        PageManager.registrationPage().get().registration(newUser, AlertType.EMPTY_FIELDS);
        assertTrue(PageManager.registrationPage().isModalDisplayed(true), "Modal is hidden");

    }
}
