package selenide_tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.PageManager;
import pages.commonComponents.NavBarComponent;
import pages.loginPage.factory.LoginPageFactory;

import enums.AlertType;
import pages.loginPage.LoginPage;
import utils.Mocks;
import valueObjects.User;
import valueObjects.UserRegistry;

import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.tomakehurst.wiremock.WireMockServer;


@DisplayName("Login logic tests")
public class LoginTests extends BaseTest {

    private User newUser;
    private LoginPage loginPageFactory;
    private final User DEFAULT_USER = UserRegistry.get("default");
    private WireMockServer wireMockServer;
    private NavBarComponent navBarComponent;

    @BeforeEach
    void setUpPage() {
        UserRegistry.createRandomUser("new");
        wireMockServer = Mocks.startFlagMock();
        navBarComponent = new NavBarComponent();
    }

    @AfterEach
    void tearDown() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful login")
    @Tag("regress")
    @Tag("smoke")
    void successfulLogin() {
        navBarComponent.clickLogin();
        PageManager.loginPage().get().login(DEFAULT_USER);
        PageManager.loginPage().isModalVisible();
        navBarComponent.shouldShowUserName(true);
    }

    @ParameterizedTest
    @CsvSource({"A", "B"})
    @Severity(CRITICAL)
    @DisplayName("Successful login for redesign")
    @Tag("regress")
    @Tag("smoke")
    void testRedesign(String variant) {
        Allure.step("variant " + variant, () -> {

            navBarComponent.clickLogin();
            PageManager.customLoginPage(variant).get().login(DEFAULT_USER);
            PageManager.loginPage().isModalHidden();
            assertTrue(navBarComponent.shouldShowUserName(true), "User name is not visible");
        });

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful login in dependence of API response")
    @Tag("regress")
    @Tag("smoke")
    void testRedesignWithAPIFlag() {
        Allure.step("Check logic in according to API request", () -> {

            loginPageFactory = LoginPageFactory.getFlagFromServer();

            navBarComponent.clickLogin();
            loginPageFactory.get().login(DEFAULT_USER);
            assertTrue(navBarComponent.shouldShowUserName(true), "Username is not displayed");
        });
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with invalid data")
    @Tag("regress")
    public void errorAfterLoginInvalidCreds() {
        newUser = UserRegistry.get("new");

        navBarComponent.clickLogin();
        PageManager.loginPage().get().loginExpectingError(newUser, AlertType.USER_NOT_EXIST);
        PageManager.loginPage().isModalVisible();
        assertFalse(navBarComponent.shouldShowUserName(false), "User name is visible");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with empty data")
    @Tag("regress")
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");

        navBarComponent.clickLogin();
        PageManager.loginPage().get().loginExpectingError(newUser, AlertType.EMPTY_FIELDS);
        PageManager.loginPage().isModalVisible();
        assertFalse(navBarComponent.shouldShowUserName(false), "User name is visible");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful logout")
    @Tag("regress")
    @Tag("smoke")
    public void logout() {
        navBarComponent.clickLogin();
        PageManager.loginPage().get().login(DEFAULT_USER);
        assertFalse(navBarComponent.clickLogout()
                .shouldShowUserName(false), "User name is visible");
    }
}
