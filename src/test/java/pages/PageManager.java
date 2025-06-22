package pages;

import pages.cartPage.CartPage;
import pages.purchasePages.PurchasePage;
import pages.purchasePages.SuccessPurchasePage;
import pages.itemPage.ItemPage;
import pages.loginPage.LoginPageOptionA;
import pages.loginPage.LoginPageOptionB;
import pages.mainPage.MainPage;
import pages.loginPage.LoginPage;
import pages.registrationPage.RegistrationPage;

public class PageManager {

    private static LoginPageOptionA loginPage;
    private static LoginPage customLoginPage;
    private static RegistrationPage registrationPage;
    private static MainPage mainPage;
    private static CartPage cartPage;
    private static SuccessPurchasePage successPurchasePage;
    private static PurchasePage purchasePage;

    public static LoginPageOptionA loginPage() {
        if (loginPage == null) loginPage = new LoginPageOptionA();
        return loginPage;
    }

    public static LoginPage customLoginPage(String variant) {
        if (customLoginPage == null && "A".equals(variant)) {
            return new LoginPageOptionA();
        }
        return new LoginPageOptionB();

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
        customLoginPage = null;

    }

}
