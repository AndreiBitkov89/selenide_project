package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.components.*;
import selenide.helpers.Brands;

import java.util.List;
import static io.qameta.allure.SeverityLevel.*;

public class MainPageTests extends BaseTest {

    private CategoryFilterComponent filterPage = new CategoryFilterComponent();
    private static List<String> filteredItems;

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnPhones() {

        filteredItems = filterPage.filterPhones();

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedPhones());
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnLaptops() {

        filteredItems = filterPage.filterLaptops();

        filterPage.assertFilteredItems(filteredItems,  Brands.getAllowedLaptops());
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnMonitors() {

        filteredItems = filterPage.filterMonitors();

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

    }
}


