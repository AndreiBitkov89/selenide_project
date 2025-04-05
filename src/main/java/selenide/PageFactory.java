package selenide;

import selenide.pages.*;

public class PageFactory {

    private static LoginPage loginPage;
    private static RegistrationPage registrationPage;
    private static MainPage mainPage;
    private static CartPage cartPage;
    private static ItemPage itemPage;

    public static LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage();
        return loginPage;
    }

    public static RegistrationPage registrationPage() {
        if (registrationPage == null) registrationPage = new RegistrationPage();
        return registrationPage;
    }

    public static MainPage mainPage() {
        if (mainPage == null) mainPage = new MainPage();
        return mainPage;
    }

    public static CartPage cartPage() {
        if (cartPage == null) cartPage = new CartPage();
        return cartPage;
    }

    public static ItemPage itemPage(String title) {
        if (itemPage == null) itemPage = new ItemPage(title);

        return itemPage;
    }

    public static void reset() {
        loginPage = null;
        registrationPage = null;
        mainPage = null;
        cartPage = null;
        itemPage = null;
    }
}
