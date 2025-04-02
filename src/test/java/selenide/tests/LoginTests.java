package selenide.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.LoginPage;
import selenide.pages.MainPage;
import selenide.components.Alerts;
import selenide.components.NavBar;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private MainPage mainPage = new MainPage();
    private LoginPage loginPage = new LoginPage();
    private final String TEST_USER = "TestNameUser";
    private final String TEST_PASS = "TestPass";
    private Faker USER_BILDER = new Faker();

    @Test
    @Severity(CRITICAL)
    void successfulLogin() {
        NavBar.LOGIN.gotoNavBar();
        loginPage.login(TEST_USER, TEST_PASS);
        loginPage.getModal().shouldBe(visible);

        assertNotNull(loginPage.getCookie("tokenp_"));

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginInvalidCreds() {

        String name = USER_BILDER.name().username();
        String pass = USER_BILDER.internet().password();

        NavBar.LOGIN.gotoNavBar();
        loginPage.fakeLogin(name, pass, Alerts.USER_NOT_EXIST);
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);
        assertNull(loginPage.getCookie("tokenp_"));

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginEmptyCreds() {

        NavBar.LOGIN.gotoNavBar();
        loginPage.fakeLogin("", "", Alerts.EMPTY_FIELDS);

        mainPage.getUsernameAfterLogin().shouldNotBe(visible);
        assertNull(loginPage.getCookie("tokenp_"));

    }

}
