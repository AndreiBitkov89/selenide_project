package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import pages.itempage.ItemPage;
import pages.mainpage.Categories;
import pages.mainpage.CategoryFilter;
import pages.mainpage.MainPage;
import valueObjects.Brands;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;

@DisplayName("Filter logic tests")
public class MainPageTests extends BaseTest {

    private CategoryFilter filterPage;
    private MainPage mainPage;
    private ItemPage itemPage;

    @BeforeEach
    public void setUp() {
        mainPage = PageManager.mainPage();
        filterPage = new CategoryFilter();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter phones")
    @Tag("regress")
    @Tag("smoke")
    void shouldFilterItemsAndReturnPhones() {
        List<String> filteredItems = filterPage.extractTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(Categories.PHONES.getMESSAGE())
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedPhones());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter laptops")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnLaptops() {
        List<String> filteredItems = filterPage.extractTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(Categories.LAPTOPS.getMESSAGE())
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedLaptops());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter monitors")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnMonitors() {
        List<String> filteredItems = filterPage.extractTitles(
                filterPage.filterAndReturnProductElements(
                        filterPage.getCategory(Categories.MONITORS.getMESSAGE())
                )
        );
        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by index")
    @Tag("regress")
    public void shouldOpenCorrectItemByIndex() {
        String item = "Nokia lumia 1520";
        itemPage = PageManager.itemPage(item);
        mainPage.get().getProductByIndex(1).openItem();
        Assertions.assertEquals(item, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by name")
    @Tag("regress")
    public void shouldOpenCorrectItemByName() {
        String item = "Sony xperia z5";
        itemPage = PageManager.itemPage(item);
        mainPage.get().getProductByTitle(item, 0).openItem();
        Assertions.assertEquals(item, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the cheapest item")
    @Tag("regress")
    public void shouldReturnCheapestItem() {
        String expectedItem = "Sony xperia z5";
        String actualItem = mainPage.get().getCheapestProduct().getTitle();
        Assertions.assertEquals(expectedItem, actualItem);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the most expensive item")
    @Tag("regress")
    public void shouldReturnTheMostExpensiveItem() {
        String expectedItem = "Nokia lumia 1520";
        String actualItem = mainPage.get().getMostExpensiveProduct().getTitle();
        Assertions.assertEquals(expectedItem, actualItem);
    }
}