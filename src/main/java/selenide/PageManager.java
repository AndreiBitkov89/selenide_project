package selenide;

import selenide.webpages.*;

public class PageManager {

    private static LoginPage loginPage;
    private static RegistrationPage registrationPage;
    private static MainPage mainPage;
    private static CartPage cartPage;
    private static SuccessPurchasePage successPurchasePage;
    private static PurchasePage purchasePage;

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

    public static SuccessPurchasePage successPurchasePage() {
        if (successPurchasePage == null) successPurchasePage = new SuccessPurchasePage();
        return successPurchasePage;
    }


    public static PurchasePage purchasePage() {
        if (purchasePage == null) purchasePage = new PurchasePage();
        return purchasePage;
    }


    public static ItemPage itemPage(String title) {
        return new ItemPage(title);
    }

    public static void reset() {
        loginPage = null;
        registrationPage = null;
        mainPage = null;
        cartPage = null;
        purchasePage = null;
        successPurchasePage = null;

    }

}
