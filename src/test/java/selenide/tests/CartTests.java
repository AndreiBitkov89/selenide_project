package selenide.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.devtools.v85.page.Page;
import selenide.PageFactory;
import selenide.components.CategoryFilterComponent;
import selenide.components.NavBarComponent;
import selenide.helpers.Brands;
import selenide.helpers.Item;
import selenide.pages.CartPage;
import selenide.pages.ItemPage;
import selenide.pages.MainPage;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private NavBarComponent navBarComponent = new NavBarComponent();
    private CartPage cartPage = PageFactory.cartPage();
    private ItemPage itemPage;

    private final SelenideElement cardLocator = navBarComponent.getCart();
    private Item item;
    private CategoryFilterComponent filterPage = new CategoryFilterComponent();
    private static List<String> filteredItems;
    private int itemPrice;

    @Test
    @Severity(CRITICAL)
    public void shouldAddItemToCart() {
        Item item = new Item("Samsung galaxy s7", 800);
        itemPage = PageFactory.itemPage(item.getItemTitle()).get();

        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        navBarComponent.getCart().shouldBe(enabled).click();
        cartPage.getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }

    @Test
    @Severity(CRITICAL)
    public void filterMonitorAndAddtoCart() {
        item = new Item("Apple monitor 24", 400);

        filteredItems = filterPage.filterMonitors();
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        itemPage = PageFactory.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        itemPrice = itemPage.returnPrice();
        assertEquals(item.getItemPrice(), itemPrice);

        navBarComponent.goTo(cardLocator);
        cartPage.getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }
}
