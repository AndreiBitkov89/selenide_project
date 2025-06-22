package selenide_tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import enums.ItemCategory;
import pages.mainPage.CategoryFilter;
import pages.mainPage.MainPage;
import selenide_tests.cartSteps.Steps;
import valueObjects.Brands;
import java.util.List;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static selenide_tests.TestData.TestData.*;

@DisplayName("Cart logic tests")
public class CartTests extends BaseTest {

    private MainPage mainPage;
    private CategoryFilter filterPage;

    @BeforeEach
    void setUpPage() {
        mainPage = PageManager.mainPage();
        filterPage = new CategoryFilter(mainPage);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Add item to cart and create order")
    @Tag("regress")
    @Tag("smoke")
    void shouldAddItemToCart() {
        Steps steps = new Steps();

        steps.addItemToCart(GALAXY_S7);
        steps.goToCartAndCheckItem(GALAXY_S7);
        steps.completePurchase(DEFAULT_PURCHASE);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Filter items and add item to cart")
    @Tag("regress")
    void filterMonitorAndAddToCart() {
        Steps steps = new Steps();

        List<String> filteredItems = filterPage.getTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(ItemCategory.MONITORS.getMessage()).getText()
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

        steps.addItemToCart(APPLE_MONITOR);
        steps.goToCartAndCheckItem(APPLE_MONITOR);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Remove item from cart")
    @Tag("regress")
    @Tag("smoke")
    void deleteItemInCart() {
        Steps steps = new Steps();

        steps.addItemToCart(GALAXY_S7);
        steps.deleteItemFromCart(GALAXY_S7);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Prices summarising in cart")
    @Tag("regress")
    @Tag("smoke")
    void addTwoItemsAndCheckSumPrices() {
        Steps steps = new Steps();
        int expectedTotal = NOKIA_PHONE.getItemPrice() + HTC_PHONE.getItemPrice();

        steps.addItemToCart(NOKIA_PHONE);
        PageManager.mainPage().openMain().get();
        steps.addItemToCart(HTC_PHONE);
        steps.checkTotalPrice(expectedTotal);
    }
}
