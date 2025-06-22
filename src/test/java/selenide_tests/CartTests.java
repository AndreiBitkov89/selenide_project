package selenide_tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import enums.ItemCategory;
import pages.mainPage.CategoryFilter;
import pages.commonComponents.NavBarComponent;
import pages.cartPage.CartPage;
import pages.itemPage.ItemPage;
import pages.mainPage.MainPage;
import pages.purchasePages.PurchasePage;
import pages.purchasePages.SuccessPurchasePage;
import valueObjects.Brands;

import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;
import static selenide_tests.TestData.TestData.*;

@DisplayName("Cart logic tests")
public class CartTests extends BaseTest {

    //todo рассказаь про минусы с несколькими тестовыми методами
    private NavBarComponent navBar;
    private CartPage cartPage;
    private MainPage mainPage;
    private PurchasePage purchasePage;
    private SuccessPurchasePage successPage;
    private CategoryFilter filterPage;
    private ItemPage itemPage;

    @BeforeEach
    void setUpPage() {
        navBar = new NavBarComponent();
        cartPage = PageManager.cartPage();
        purchasePage = PageManager.purchasePage();
        successPage = PageManager.successPurchasePage();
        mainPage = PageManager.mainPage();
        filterPage = new CategoryFilter(mainPage);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Add item to cart and create order")
    @Tag("regress")
    @Tag("smoke")
    void shouldAddItemToCart() {
        PageManager.mainPage().gotoItemPage(GALAXY_S7.getItemTitle());
        itemPage = PageManager.itemPage(GALAXY_S7.getItemTitle()).get();
        assertEquals(GALAXY_S7.getItemTitle(), itemPage.getItemName(), "Item name on item page is incorrect");
        itemPage.addItemInCart();
        assertEquals(GALAXY_S7.getItemPrice(), itemPage.getPrice(),  "Item price is incorrect");

        navBar.clickCart();
        assertTrue(cartPage.isItemInCart(GALAXY_S7.getItemTitle(), true), "Title does not match");
        assertEquals(GALAXY_S7.getItemPrice(), cartPage.getPriceOfItemInCart(GALAXY_S7.getItemTitle()), "Title does not match");

        PageManager.cartPage().goToOrder();
        purchasePage.get().fillForm(DEFAULT_PURCHASE).submitPurchase();
        assertTrue(successPage.isThankYouVisible(), "Thank You title is not visible");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Filter items and add item to cart")
    @Tag("regress")
    void filterMonitorAndAddToCart() {
        List<String> filteredItems = filterPage.getTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(ItemCategory.MONITORS.getMessage()).getText()
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        mainPage.gotoItemPage(APPLE_MONITOR.getItemTitle());
        itemPage = PageManager.itemPage(APPLE_MONITOR.getItemTitle()).get();
        assertEquals(APPLE_MONITOR.getItemTitle(), itemPage.getItemName(), "Title does not match");
        itemPage.addItemInCart();
        assertEquals(APPLE_MONITOR.getItemPrice(), itemPage.getPrice(),  "Price is incorrect");

        navBar.clickCart();
        assertTrue(cartPage.get().isItemInCart(APPLE_MONITOR.getItemTitle(), true), "Item was not found");
        assertEquals(APPLE_MONITOR.getItemPrice(), cartPage.getPriceOfItemInCart(APPLE_MONITOR.getItemTitle()), "Price in card is incorrect");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Remove item from cart")
    @Tag("regress")
    @Tag("smoke")
    void deleteItemInCart() {
        PageManager.mainPage().gotoItemPage(NOKIA_PHONE.getItemTitle());
        itemPage = PageManager.itemPage(NOKIA_PHONE.getItemTitle()).get();

        assertEquals(NOKIA_PHONE.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(NOKIA_PHONE.getItemPrice(), itemPage.getPrice(),  "Item name on item page is incorrect");

        navBar.clickCart();
        assertFalse(cartPage.get().deleteItemFromCart(NOKIA_PHONE.getItemTitle())
                .isItemInCart(NOKIA_PHONE.getItemTitle(), false), "Item still present in cart after deletion");
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Prices summarising in cart")
    @Tag("regress")
    @Tag("smoke")
    void addTwoItemsAndCheckSumPrices() {
        PageManager.mainPage().gotoItemPage(NOKIA_PHONE.getItemTitle());
        PageManager.itemPage(NOKIA_PHONE.getItemTitle()).get().addItemInCart();
        PageManager.mainPage().openMain().get();
        PageManager.mainPage().gotoItemPage(HTC_PHONE.getItemTitle());
        PageManager.itemPage(HTC_PHONE.getItemTitle()).get().addItemInCart();

        navBar.clickCart();
        int expectedTotal = NOKIA_PHONE.getItemPrice() + HTC_PHONE.getItemPrice();

        assertTrue(PageManager.cartPage().checkTotalPrice(expectedTotal), "Total price in cart does not match with expected");
    }
}
