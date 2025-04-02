package selenide.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.w3c.dom.ls.LSOutput;
import selenide.pages.LoginPage;
import selenide.pages.MainPage;
import selenide.pages.RegistrationPage;
import selenide.components.Alerts;
import selenide.components.NavBar;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationTests extends BaseTest {

    private final Faker USER_BILDER = new Faker();
    private static final String TEST_USER = "TestNameUser";
    private static final String TEST_PASS = "TestPass";
    private static MainPage mainPage = new MainPage();
    private static LoginPage loginPage = new LoginPage();
    private static RegistrationPage registrationPage = new RegistrationPage();
    private static String name;
    private static String pass;

    @Test
    @Severity(CRITICAL)
    public void shouldRegisterClientAndAuthorize() {

        name = USER_BILDER.name().username();
        pass = USER_BILDER.internet().password();

        NavBar.SIGN.gotoNavBar();
        registrationPage.registration(name, pass, Alerts.SUCCESSFUL_SIGN);
        NavBar.LOGIN.gotoNavBar();

        loginPage.login(name, pass);

        mainPage.shouldShowWelcome(name);
        assertNotNull(loginPage.getCookie("tokenp_"));
    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithExistedCreds() {

        NavBar.SIGN.gotoNavBar();

        registrationPage.registration(TEST_USER, TEST_PASS, Alerts.USER_ALREADY_EXIST);
        registrationPage.getModal().shouldNotBe(hidden);

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterRegWithEmptyCreds() {

        NavBar.SIGN.gotoNavBar();

        registrationPage.registration("", "", Alerts.EMPTY_FIELDS);
        registrationPage.getModal().shouldNotBe(hidden);

    }

}
