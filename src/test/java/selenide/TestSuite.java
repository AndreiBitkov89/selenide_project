package selenide;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestSuite {

    String url = "https://www.demoblaze.com/";
    Faker faker = new Faker();
    MainPage mainPage;
    LoginPage loginPage;
    ItemPage itemPage;
    CartPage cartPage;
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
        itemPage = new ItemPage();
        cartPage = new CartPage();

    }

    @AfterEach
    void clearState() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear()");
    }

    @Test
    @Severity(CRITICAL)
    void shouldRegisterClientAndAuthoriseHim() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        registrationPage.registration(name, pass, "Sign up successful.");
        mainPage.waitUntilLoaded();
        loginPage.login(name, pass);

        mainPage.shouldShowWelcome(name);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterRegWithExistedCreds() {

        registrationPage.registration(testUser, testPass, "This user already exist.");
        registrationPage.getModal().shouldBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterRegWithEmptyCreds() {

        registrationPage.registration("", "", "Please fill out Username and Password.");
        registrationPage.getModal().shouldBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterLoginInvalidCreds() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        loginPage.fakeLogin(name, pass, "User does not exist.");
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterLoginEmptyCreds() {

        loginPage.fakeLogin("", "", "Please fill out Username and Password.");
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnPhones() {

        List<String> filteredItems = mainPage.filterItems("phone");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("samsung", "nokia", "nexus", "iphone", "sony", "htc");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnLaptops() {

        List<String> filteredItems = mainPage.filterItems("laptop");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("sony vaio", "macbook", "dell");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }


    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnMonitors() {

        List<String> filteredItems = mainPage.filterItems("monitor");

        assertFalse(filteredItems.isEmpty());

        List<String> allowedBrands = List.of("apple", "asus");

        for (String item : filteredItems) {
            String lowerItem = item.toLowerCase();
            boolean matchesBrand = allowedBrands.stream().anyMatch(lowerItem::contains);
            assertTrue(matchesBrand);
        }

    }

    @Test
    @Severity(CRITICAL)
    void shouldAddItemToCart() {
        String item = "Samsung galaxy s7";

        mainPage.gotoItem(item);
        assertEquals(item, itemPage.getItemName());

        itemPage.addItemToCart();
        mainPage.gotoNavBar("cart");

        String itemNameInCart = cartPage.getItemName();
        assertEquals(item, itemNameInCart);
    }
}
