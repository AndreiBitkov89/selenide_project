package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.components.*;
import selenide.valueObject.Brands;

import java.util.List;
import static io.qameta.allure.SeverityLevel.*;

public class MainPageTests extends BaseTest {

    private CategoryFilter filterPage = new CategoryFilter();
    private static List<String> filteredItems;

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnPhones() {

        filteredItems = filterPage.filterAndReturnItems(filterPage.getPHONE());

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedPhones());
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnLaptops() {

        filteredItems = filterPage.filterAndReturnItems(filterPage.getLAPTOP());

        filterPage.assertFilteredItems(filteredItems,  Brands.getAllowedLaptops());
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnMonitors() {

        filteredItems = filterPage.filterAndReturnItems(filterPage.getMONITOR());

        filterPage.assertFilteredItems(filteredItems, Brands.getAllowedMonitors());

    }
}


