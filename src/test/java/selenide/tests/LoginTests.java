package selenide.tests;

import helpers.CredentialsGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.PageManager;
import pages.commonComponents.NavBarComponent;
import pages.loginpage.factory.LoginPageFactory;

import helpers.AlertTypes;
import pages.loginpage.LoginPage;
import valueObjects.User;
import pages.cart.CartPage;
import pages.cart.PurchasePage;
import pages.cart.SuccessPurchasePage;

import static com.codeborne.selenide.Condition.*;
import static config.ConfigProvider.CONFIG;
import static io.qameta.allure.SeverityLevel.*;

@DisplayName("Login logic tests")
public class LoginTests extends BaseTest {

    private final String DEFAULT_LOGIN = CONFIG.username();
    private final String DEFAULT_PASS = CONFIG.password();
    private final User DEFAULT_USER = new User(DEFAULT_LOGIN, DEFAULT_PASS);
    private String randomUsername;
    private String randomPassword;
    private User newUser;
    private NavBarComponent navBarComponent;
    private CartPage cartPage;
    private PurchasePage purchasePage;
    private SuccessPurchasePage successPage;
    private LoginPage loginPageFactory;

    @BeforeEach
    void setUpPage() {
        randomUsername = CredentialsGenerator.generateUsername(5);
        randomPassword = CredentialsGenerator.generatePassword(8);
        cartPage = PageManager.cartPage();
        navBarComponent = new NavBarComponent();
        purchasePage = PageManager.purchasePage();
        successPage = PageManager.successPurchasePage();
    }


    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful login")
    @Tag("regress")
    @Tag("smoke")
    void successfulLogin() {
        PageManager.loginPage().get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.usernameAfterLogin().shouldBe(visible);
    }

    @ParameterizedTest
    @CsvSource({"A", "B"})
    @Severity(CRITICAL)
    @DisplayName("Successful login for redesign")
    @Tag("regress")
    @Tag("smoke")
    void testRedesign(String variant) {
        Allure.step("variant " + variant, () -> {
            PageManager.customLoginPage(variant).get().login(DEFAULT_USER).getModal().shouldBe(hidden);
            navBarComponent.usernameAfterLogin().shouldBe(visible);
        });

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful login in dependence of API response")
    @Tag("regress")
    @Tag("smoke")
    @Disabled
    void testRedesignWithAPIFlag() {
        Allure.step("Check logic in according to API request", () -> {
            loginPageFactory = LoginPageFactory.getFlagFromServer();
            loginPageFactory.get().login(DEFAULT_USER).getModal().shouldBe(hidden);
            navBarComponent.usernameAfterLogin().shouldBe(visible);
        });
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with invalid data")
    @Tag("regress")
    public void errorAfterLoginInvalidCreds() {

        newUser = new User(randomUsername, randomPassword);
        PageManager.loginPage().get().wrongLogin(newUser, AlertTypes.USER_NOT_EXIST);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Error after login with empty data")
    @Tag("regress")
    public void errorAfterLoginEmptyCreds() {
        newUser = new User("", "");
        PageManager.loginPage().get().wrongLogin(newUser, AlertTypes.EMPTY_FIELDS);
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful logout")
    @Tag("regress")
    @Tag("smoke")
    public void logout() {
        PageManager.loginPage().get().login(DEFAULT_USER).getModal().shouldBe(hidden);
        navBarComponent.goTo(navBarComponent.logout()).usernameAfterLogin().shouldBe(hidden);
    }
}
