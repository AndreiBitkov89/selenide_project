package pages;

import pages.cart.CartPage;
import pages.cart.PurchasePage;
import pages.cart.SuccessPurchasePage;
import pages.itempage.ItemPage;
import pages.loginpage.LoginPageOptionA;
import pages.loginpage.LoginPageOptionB;
import pages.mainpage.MainPage;
import pages.loginpage.LoginPage;
import pages.registrationpage.RegistrationPage;

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
