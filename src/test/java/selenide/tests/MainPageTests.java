package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.PageManager;
import pages.itempage.ItemPage;
import pages.mainpage.CategoryFilter;
import pages.mainpage.MainPage;
import pages.mainpage.ProductCardElement;
import valueObjects.Brands;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;

@DisplayName("Filter logic tests")
public class MainPageTests extends BaseTest {

    private CategoryFilter filterPage = new CategoryFilter();
    private static List<String> filteredItems;
    private MainPage mainPage;
    private ItemPage itemPage;

    @BeforeEach
    public void setUp() {
        mainPage = PageManager.mainPage();
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter phones")
    @Tag("regress")
    @Tag("smoke")
    void shouldFilterItemsAndReturnPhones() {

        filteredItems = filterPage.extractTitles(filterPage.filterAndReturnProductElements(filterPage.getPhone()));

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedPhones());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter laptops")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnLaptops() {

        filteredItems = filterPage.extractTitles(filterPage.filterAndReturnProductElements(filterPage.getLaptop()));

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedLaptops());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Successful filter monitors")
    @Tag("regress")
    @Tag("smoke")
    public void shouldFilterItemsAndReturnMonitors() {
        filteredItems = filterPage.extractTitles(filterPage.filterAndReturnProductElements(filterPage.getMonitor()));

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by index")
    @Tag("regress")
    public void shouldOpenCorrectItemByIndex() {
        String item = "Nokia lumia 1520";
        itemPage = PageManager.itemPage(item);
        mainPage.get().getProductByIndex(1).click();
        Assertions.assertEquals(item, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Open item page by name")
    @Tag("regress")
    public void shouldOpenCorrectItemByName() {
        String item = "Sony xperia z5";
        itemPage = PageManager.itemPage(item);
        mainPage.get().getProductByTitle(item).click();
        Assertions.assertEquals(item, itemPage.getItemName());
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the cheapest item")
    @Tag("regress")
    public void shouldReturnCheapestItem() {
        String item = "Sony xperia z5";
        String title = mainPage.get().getCheapestProduct().getTitle();
        Assertions.assertEquals(item, title);
    }

    @Test
    @Severity(CRITICAL)
    @DisplayName("Find the most expensive item")
    @Tag("regress")
    public void shouldReturnTheMostExpensiveItem() {
        String item = "Nokia lumia 1520";
        String title = mainPage.get().getMostExpensiveProduct().getTitle();
        Assertions.assertEquals(item, title);
    }


}


