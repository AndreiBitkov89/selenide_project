package selenide_tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import pages.itempage.ItemPage;
import constants.Categories;
import pages.mainpage.CategoryFilter;
import pages.mainpage.MainPage;
import steps.FilterItemsSteps;
import valueObjects.Brands;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;

@DisplayName("Filter logic tests")
public class MainPageTests extends BaseTest {

    private CategoryFilter filterPage;
    private MainPage mainPage;
    private ItemPage itemPage;
    private FilterItemsSteps sharedSteps;
    private final String XPERIA_PHONE = "Sony xperia z5";
    private final String NOKIA_PHONE = "Nokia lumia 1520";

    @BeforeEach
    public void setUp() {
        mainPage = PageManager.mainPage();
        filterPage = new CategoryFilter();
        sharedSteps = new FilterItemsSteps();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter phones")
    @Tag("regress")
    @Tag("smoke")
    void shouldFilterItemsAndReturnPhones() {

        List<String> filteredItems = sharedSteps.applyFilter(Categories.PHONES);
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedPhones());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter laptops")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnLaptops() {
        List<String> filteredItems = sharedSteps.applyFilter(Categories.LAPTOPS);
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedLaptops());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter monitors")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnMonitors() {
        List<String> filteredItems = sharedSteps.applyFilter(Categories.MONITORS);
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by index")
    @Tag("regress")
    public void shouldOpenCorrectItemByIndex() {
        itemPage = PageManager.itemPage(NOKIA_PHONE);
        mainPage.get().getProductByIndex(1).openItem();
        Assertions.assertEquals(NOKIA_PHONE, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by name")
    @Tag("regress")
    public void shouldOpenCorrectItemByName() {

        itemPage = PageManager.itemPage(XPERIA_PHONE);
        mainPage.get().getProductByTitle(XPERIA_PHONE, 0).openItem();
        Assertions.assertEquals(XPERIA_PHONE, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the cheapest item")
    @Tag("regress")
    public void shouldReturnCheapestItem() {
        String actualItem = mainPage.get().getCheapestProduct().getTitle();
        Assertions.assertEquals(XPERIA_PHONE, actualItem);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the most expensive item")
    @Tag("regress")
    public void shouldReturnTheMostExpensiveItem() {
        String actualItem = mainPage.get().getMostExpensiveProduct().getTitle();
        Assertions.assertEquals(NOKIA_PHONE, actualItem);
    }
}