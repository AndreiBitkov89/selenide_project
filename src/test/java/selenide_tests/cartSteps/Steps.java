package selenide_tests.cartSteps;

import io.qameta.allure.Allure;
import pages.PageManager;
import pages.cartPage.CartPage;
import pages.commonComponents.NavBarComponent;
import pages.itemPage.ItemPage;
import pages.mainPage.MainPage;
import pages.purchasePages.PurchasePage;
import pages.purchasePages.SuccessPurchasePage;
import valueObjects.Item;
import valueObjects.Purchase;

import static org.junit.jupiter.api.Assertions.*;

public class Steps {

    private final NavBarComponent navBar = new NavBarComponent();
    private final MainPage mainPage = PageManager.mainPage();
    private final CartPage cartPage = PageManager.cartPage();
    private final PurchasePage purchasePage = PageManager.purchasePage();
    private final SuccessPurchasePage successPage = PageManager.successPurchasePage();

    public void addItemToCart(Item item) {
        mainPage.gotoItemPage(item.getItemTitle());
        ItemPage itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName(), "Item name on item page is incorrect");
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.getPrice(), "Item price on item page is incorrect");
    }

    public void goToCartAndCheckItem(Item item) {
        navBar.clickCart();
        assertTrue(cartPage.isItemInCart(item.getItemTitle(), true));
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }

    public void completePurchase(Purchase purchase) {
        cartPage.goToOrder();
        purchasePage.get().fillForm(purchase).submitPurchase();
        assertTrue(successPage.isThankYouVisible());
    }

    public void deleteItemFromCart(Item item) {
        navBar.clickCart();
        cartPage.deleteItemFromCart(item.getItemTitle());
        assertFalse(cartPage.isItemInCart(item.getItemTitle(), false), "Item still present in cart after deletion");
    }

    public void checkTotalPrice(int expectedTotal) {
        navBar.clickCart();
        assertTrue(cartPage.checkTotalPrice(expectedTotal), "Total price is different from expected");
    }
}