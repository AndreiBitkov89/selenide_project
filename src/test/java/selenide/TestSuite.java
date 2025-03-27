package selenide;

import com.codeborne.selenide.Configuration;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestSuite {

    String url = "https://www.demoblaze.com/";
    Faker faker = new Faker();
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

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
    void shouldRegisterClient() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        registrationPage.registration(name, pass, "Sign up successful.");
        loginPage.login(name, pass);
        mainPage.shouldShowWelcome(name);

    }
}
