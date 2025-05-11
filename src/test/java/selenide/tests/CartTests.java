package selenide.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import pages.mainpage.CategoryFilter;
import pages.commonComponents.NavBarComponent;
import pages.cart.CartPage;
import pages.itempage.ItemPage;
import pages.cart.PurchasePage;
import pages.cart.SuccessPurchasePage;
import valueObjects.Brands;
import valueObjects.Item;
import valueObjects.Purchase;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.hidden;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart logic tests")
public class CartTests extends BaseTest {

    private NavBarComponent navBar;
    private CartPage cartPage;
    private PurchasePage purchasePage;
    private SuccessPurchasePage successPage;
    private SelenideElement cartButton;
    private CategoryFilter filterPage;
    private Purchase defaultPurchase = new Purchase("Qa", "Germany", "Berlin", "1234567", "01", "2026");;
    private List<String> filteredItems;
    private int expectedTotal;

    @BeforeEach
    void setUpPage() {
        navBar = new NavBarComponent();
        cartButton = navBar.getCart();
        cartPage = PageManager.cartPage();
        purchasePage = PageManager.purchasePage();
        successPage = PageManager.successPurchasePage();
        filterPage = new CategoryFilter();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Add item to cart and create order")
    @Tag("regress")
    @Tag("smoke")
    void shouldAddItemToCart() {
        Item item = new Item("Samsung galaxy s7", 800);
        ItemPage itemPage = PageManager.itemPage(item.getItemTitle()).get();

        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        cartButton.click();
        cartPage.getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));

        purchasePage.get().fillForm(defaultPurchase).submitPurchase();
        successPage.getThanyouText().shouldBe(visible);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Filter items and add item to cart")
    @Tag("regress")
    void filterMonitorAndAddToCart() {
        Item item = new Item("Apple monitor 24", 400);

        filteredItems = filterPage.extractTitles(filterPage.filterAndReturnProductElements(filterPage.getMonitor()));
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        ItemPage itemPage = PageManager.itemPage(item.getItemTitle()).get();
        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        cartPage.get().getItemInCart(item.getItemTitle()).shouldBe(visible);
        assertEquals(item.getItemPrice(), cartPage.getPriceOfItemInCart(item.getItemTitle()));
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Remove item from cart")
    @Tag("regress")
    @Tag("smoke")
    void deleteItemInCart() {
        Item item = new Item("Nokia lumia 1520", 820);
        ItemPage itemPage = PageManager.itemPage(item.getItemTitle()).get();

        assertEquals(item.getItemTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(item.getItemPrice(), itemPage.returnPrice());

        cartPage.get().deleteItemFromCart(item.getItemTitle())
                .getItemInCart(item.getItemTitle()).shouldBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Prices summarising in cart")
    @Tag("regress")
    @Tag("smoke")
    void addTwoItemsAndCheckSumPrices() {
        Item item1 = new Item("Nokia lumia 1520", 820);
        Item item2 = new Item("HTC One M9", 700);

        PageManager.itemPage(item1.getItemTitle()).get().addItemInCart();
        PageManager.mainPage().get();
        PageManager.itemPage(item2.getItemTitle()).get().addItemInCart();

        navBar.goTo(cartButton);
        expectedTotal = item1.getItemPrice() + item2.getItemPrice();
        PageManager.cartPage().waitUntilTotalPriceEquals(expectedTotal);
    }
}
