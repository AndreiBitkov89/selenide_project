package selenide.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.PageManager;
import selenide.components.CategoryFilter;
import selenide.components.NavBarComponent;
import selenide.valueObject.Brands;
import selenide.valueObject.Item;
import selenide.pages.CartPage;
import selenide.pages.ItemPage;
import selenide.pages.MainPage;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private NavBarComponent navBarComponent = new NavBarComponent();
    private CartPage cartPage = PageManager.cartPage();
    private ItemPage itemPage;
    private MainPage mainPage = new MainPage();

    private final SelenideElement cardLocator = navBarComponent.getCart();
    private CategoryFilter filterPage = new CategoryFilter();
    private static List<String> filteredItems;

    @Test
    @Severity(CRITICAL)
    public void shouldAddItemToCart() {
        Item item = new Item("Samsung galaxy s7", 800);
        itemPage = PageManager.itemPage(item.getItemTitle()).get();

        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        navBarComponent.getCart().shouldBe(enabled).click();
        cartPage.getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }

    @Test
    @Severity(CRITICAL)
    public void filterMonitorAndAddToCart() {
        Item item = new Item("Apple monitor 24", 400);

        filteredItems = filterPage.filterAndReturnItems(filterPage.getMONITOR());
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        navBarComponent.goTo(cardLocator);
        cartPage.get().getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }

    @Test
    @Severity(CRITICAL)
    public void deleteItemInCart() {
        Item item = new Item("Nokia lumia 1520", 820);

        itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        navBarComponent.goTo(cardLocator);
        cartPage.get().deleteItemFromCart(item.getItemTitle()).getItemInCart(item.getItemTitle()).shouldBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    public void addTwoItemsAndCheckSumPrices() {
        Item item1 = new Item("Nokia lumia 1520", 820);
        Item item2 = new Item("HTC One M9", 700);

        ItemPage itemPage = PageManager.itemPage(item1.getItemTitle()).get();
        assertEquals(item1.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        mainPage.get();
        ItemPage itemPage2 = PageManager.itemPage(item2.getItemTitle()).get();
        assertEquals(item2.getItemTitle(), itemPage2.getItemName());
        itemPage2.addItemInCart();

        navBarComponent.goTo(cardLocator);
        assertEquals(item1.getItemPrice() + item2.getItemPrice(), cartPage.getTotalPrice());

    }
}
