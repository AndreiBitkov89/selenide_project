package selenide_tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.loginpage.factory.LoginPageFactory;

import constants.AlertTypes;
import pages.loginpage.LoginPage;
import steps.LoginSteps;
import utils.Mocks;
import valueObjects.User;
import valueObjects.UserRegistry;

import static io.qameta.allure.SeverityLevel.*;
import com.github.tomakehurst.wiremock.WireMockServer;


@DisplayName("Login logic tests")
public class LoginTests extends BaseTest {

    private User newUser;
    private LoginPage loginPageFactory;
    private final User DEFAULT_USER = UserRegistry.get("default");
    private final LoginSteps LOGIN_STEPS = new LoginSteps();
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUpPage() {
        UserRegistry.createRandomUser("new");
        wireMockServer = Mocks.startFlagMock();
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
        LOGIN_STEPS.login(DEFAULT_USER);
        LOGIN_STEPS.shouldSeeUsername();
    }

    @ParameterizedTest
    @CsvSource({"A", "B"})
    @Severity(CRITICAL)
    @DisplayName("Successful login for redesign")
    @Tag("regress")
    @Tag("smoke")
    void testRedesign(String variant) {
        Allure.step("variant " + variant, () -> {
            LOGIN_STEPS.loginCustom(DEFAULT_USER, variant);
            LOGIN_STEPS.shouldSeeUsername();
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
            LOGIN_STEPS.loginWithFlag(loginPageFactory, DEFAULT_USER);
            LOGIN_STEPS.shouldSeeUsername();
        });
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with invalid data")
    @Tag("regress")
    public void errorAfterLoginInvalidCreds() {
        newUser = UserRegistry.get("new");
        LOGIN_STEPS.loginWithError(newUser, AlertTypes.USER_NOT_EXIST);
        LOGIN_STEPS.shouldNotSeeUsername();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with empty data")
    @Tag("regress")
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");
        LOGIN_STEPS.loginWithError(newUser, AlertTypes.EMPTY_FIELDS);
        LOGIN_STEPS.shouldNotSeeUsername();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful logout")
    @Tag("regress")
    @Tag("smoke")
    public void logout() {
        LOGIN_STEPS.login(DEFAULT_USER);
        LOGIN_STEPS.logout();
    }
}
