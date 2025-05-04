package selenide.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.PageManager;
import selenide.components.*;
import selenide.webpages.*;
import selenide.valueObject.*;


import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart logic tests")
public class CartTests extends BaseTest {

    private NavBarComponent navBarComponent = new NavBarComponent();
    private ItemPage itemPage;

    private final SelenideElement cardLocator = navBarComponent.getCart();
    private CategoryFilter filterPage = new CategoryFilter();
    private static List<String> filteredItems;

    private Purchase purchaseDate = new Purchase("Qa", "Germany", "Berlin", "1234567", "01", "2026");

    @Test
    @Severity(CRITICAL)
    @DisplayName("Add item to cart and create order")
    @Tag("regress")
    @Tag("smoke")
    public void shouldAddItemToCart() {
        Item item = new Item("Samsung galaxy s7", 800);
        itemPage = PageManager.itemPage(item.getItemTitle()).get();

        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        navBarComponent.getCart().click();
        PageManager.cartPage().getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), PageManager.cartPage().getPriceOfItemInCart(item.getItemTitle()));

        PageManager.purchasePage().get().fillForm(purchaseDate).submitPurchase();
        PageManager.successPurchasePage().getThanyouText().shouldBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Filter items and add item to cart")
    @Tag("regress")
    public void filterMonitorAndAddToCart() {
        Item item = new Item("Apple monitor 24", 400);

        filteredItems = filterPage.filterAndReturnItems(filterPage.getMONITOR());
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        PageManager.cartPage().get().getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), PageManager.cartPage().getPriceOfItemInCart(item.getItemTitle()));
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Remove item and from cart")
    @Tag("regress")
    @Tag("smoke")
    public void deleteItemInCart() {
        Item item = new Item("Nokia lumia 1520", 820);

        itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        PageManager.cartPage().get().deleteItemFromCart(item.getItemTitle()).getItemInCart(item.getItemTitle()).shouldBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Prises summarising in cart")
    @Tag("regress")
    @Tag("smoke")
    public void addTwoItemsAndCheckSumPrices() {
        Item item1 = new Item("Nokia lumia 1520", 820);
        Item item2 = new Item("HTC One M9", 700);

        ItemPage itemPage = PageManager.itemPage(item1.getItemTitle()).get();
        assertEquals(item1.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        PageManager.mainPage().get();
        ItemPage itemPage2 = PageManager.itemPage(item2.getItemTitle()).get();
        assertEquals(item2.getItemTitle(), itemPage2.getItemName());
        itemPage2.addItemInCart();

        navBarComponent.goTo(cardLocator);
        assertEquals(item1.getItemPrice() + item2.getItemPrice(), PageManager.cartPage().getTotalPrice());

    }
}
