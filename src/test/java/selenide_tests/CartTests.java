package selenide_tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import constants.Categories;
import pages.mainpage.CategoryFilter;
import pages.commonComponents.NavBarComponent;
import pages.cartpage.CartPage;
import pages.itempage.ItemPage;
import pages.purchasepages.PurchasePage;
import pages.purchasepages.SuccessPurchasePage;
import valueObjects.Brands;
import valueObjects.Item;
import valueObjects.Purchase;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.hidden;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart logic tests")
public class CartTests extends BaseTest {

    //todo рассказаь про минусы с несколькими тестовыми методами
    private NavBarComponent navBar;
    private CartPage cartPage;
    private PurchasePage purchasePage;
    private SuccessPurchasePage successPage;
    private SelenideElement cartButton;
    private CategoryFilter filterPage;
    private ItemPage itemPage;

    //todo можно константой сделать
    private final Purchase defaultPurchase = Purchase.builder()
            .withName("QA")
            .withCountry("Germany")
            .withCity("Berlin")
            .withCreditCard("1234567")
            .withMonth("01")
            .withYear("2026")
            .build();

    //todo можно константой сделать
    private final Item APPLE_MONITOR = new Item("Apple monitor 24", 400);
    private final Item NOKIA_PHONE = new Item("Nokia lumia 1520", 820);
    private final Item HTC_PHONE = new Item("HTC One M9", 700);
    private final Item GALAXY_S7 = new Item("Samsung galaxy s7", 800);

    @BeforeEach
    void setUpPage() {
        navBar = new NavBarComponent();
        cartButton = navBar.getCart();
        //todo обсудить работу со страницами
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
        itemPage = PageManager.itemPage(GALAXY_S7.getTitle()).get();

        //todo здесь и в других местах сообщение об ошибке
        assertEquals(GALAXY_S7.getTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(GALAXY_S7.getItemPrice(), itemPage.returnPrice());

        navBar.goTo(cartButton);
        cartPage.getItemInCart(GALAXY_S7.getTitle()).shouldBe(visible);
        assertEquals(GALAXY_S7.getItemPrice(), cartPage.getPriceOfItemInCart(GALAXY_S7.getTitle()));

        purchasePage.get().fillForm(defaultPurchase).submitPurchase();
        successPage.getThankYouText().shouldBe(visible, Duration.ofSeconds(10));
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Filter items and add item to cart")
    @Tag("regress")
    void filterMonitorAndAddToCart() {
        List<String> filteredItems = filterPage.extractTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(Categories.MONITORS.getMESSAGE())
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        itemPage = PageManager.itemPage(APPLE_MONITOR.getTitle()).get();
        assertEquals(APPLE_MONITOR.getTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(APPLE_MONITOR.getItemPrice(), itemPage.returnPrice());

        cartPage.get().getItemInCart(APPLE_MONITOR.getTitle()).shouldBe(visible);
        assertEquals(APPLE_MONITOR.getItemPrice(), cartPage.getPriceOfItemInCart(APPLE_MONITOR.getTitle()));
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Remove item from cart")
    @Tag("regress")
    @Tag("smoke")
    void deleteItemInCart() {
        itemPage = PageManager.itemPage(NOKIA_PHONE.getTitle()).get();

        assertEquals(NOKIA_PHONE.getTitle(), itemPage.getItemName());
        itemPage.addItemInCart();
        assertEquals(NOKIA_PHONE.getItemPrice(), itemPage.returnPrice());

        cartPage.get().deleteItemFromCart(NOKIA_PHONE.getTitle())
                .getItemInCart(NOKIA_PHONE.getTitle()).shouldBe(hidden);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Prices summarising in cart")
    @Tag("regress")
    @Tag("smoke")
    void addTwoItemsAndCheckSumPrices() {
        PageManager.itemPage(NOKIA_PHONE.getTitle()).get().addItemInCart();
        PageManager.mainPage().get();
        PageManager.itemPage(HTC_PHONE.getTitle()).get().addItemInCart();

        navBar.goTo(cartButton);
        int expectedTotal = NOKIA_PHONE.getItemPrice() + HTC_PHONE.getItemPrice();
        PageManager.cartPage().waitUntilTotalPriceEquals(expectedTotal);
    }
}
