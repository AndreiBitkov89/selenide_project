package selenide;

import com.codeborne.selenide.Configuration;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class TestSuite {

    String url = "https://www.demoblaze.com/";
    Faker faker = new Faker();
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String testUser = "TestNameUser";
    String testPass = "TestPass";

    @BeforeEach
    void initialize() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1900, 1400";
        open(url);

        mainPage = new MainPage();
        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();

    }

    @Test
    void shouldRegisterClientAndAuthoriseHim() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        registrationPage.registration(name, pass, "Sign up successful.");
        mainPage.waitMainPageToBeLoaded();
        loginPage.login(name, pass);
        mainPage.shouldShowWelcome(name);

    }

    @Test
    void errorAfterRegWithExistedCreds() {

        registrationPage.registration(testUser, testPass, "This user already exist.");
        registrationPage.getModal().shouldBe(visible, Duration.ofSeconds(3));

    }
}
