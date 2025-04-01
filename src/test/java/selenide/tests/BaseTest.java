package selenide.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import selenide.pages.*;


import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {
    MainPage mainPage;
    ItemPage itemPage;
    CartPage cartPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @BeforeEach
    public void initialize() {

        Configuration.browser = "chrome";
        Configuration.browserSize = "1900x1400";
        open("https://www.demoblaze.com/");
        mainPage = new MainPage();
        itemPage = new ItemPage();
        cartPage = new CartPage();
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();

    }

    @AfterEach
    public void clearState() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear()");
    }
}
