package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.MainPage;
import selenide.components.ItemsFilter;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;

public class MainPageTests extends BaseTest {

    private MainPage mainPage = new MainPage();

    private static final List<String> ALLOWED_MONITORS = List.of("apple", "asus");
    private static final List<String> ALLOWED_LAPTOPS = List.of("sony vaio", "macbook", "dell");
    private static final List<String> ALLOWED_PHONES = List.of("samsung", "nokia", "nexus", "iphone", "sony", "htc");
    private static List<String> filteredItems;

    @Test
    @Severity(CRITICAL)
    void shouldFilterItemsAndReturnPhones() {

        filteredItems = mainPage.filterItems(ItemsFilter.PHONE);

        mainPage.assertFilteredItems(filteredItems, ALLOWED_PHONES);
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnLaptops() {

        filteredItems = mainPage.filterItems(ItemsFilter.LAPTOP);

        mainPage.assertFilteredItems(filteredItems, ALLOWED_LAPTOPS);
    }

    @Test
    @Severity(CRITICAL)
    public void shouldFilterItemsAndReturnMonitors() {

        filteredItems = mainPage.filterItems(ItemsFilter.MONITOR);

        mainPage.assertFilteredItems(filteredItems, ALLOWED_MONITORS);

    }
}


