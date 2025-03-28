package selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Configuration.timeout = 10000;
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
        registrationPage.getModal().shouldBe(visible);

    }

    @Test
    void errorAfterRegWithEmptyCreds() {

        registrationPage.registration("", "", "Please fill out Username and Password.");
        registrationPage.getModal().shouldBe(visible);

    }

    @Test
    void shouldFilterItemsAndReturnPhones() {

        List<String> filteredItems = mainPage.filterItems("phone");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedPhoneBrands = List.of("samsung", "nokia", "nexus", "iphone", "sony", "htc");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedPhoneBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }

    @Test
    void shouldFilterItemsAndReturnLaptops() {

        List<String> filteredItems = mainPage.filterItems("laptop");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedPhoneBrands = List.of("sony vaio", "macbook", "dell");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedPhoneBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }


    @Test
    void shouldFilterItemsAndReturnMonitors() {

        List<String> filteredItems = mainPage.filterItems("monitor");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedPhoneBrands = List.of("apple", "asus");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedPhoneBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }


}
