package selenide.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public enum ItemsFilter {
    PHONE("//*[@class ='list-group']//*[text()='Phones']"), LAPTOP("//*[@class ='list-group']//*[text()='Laptops']"), MONITOR("//*[@class ='list-group']//*[text()='Monitors']");

    private final String selector;
    private static List<String> initialItems;
    private static final ElementsCollection item = $$(".card-title a");

    ItemsFilter(String selector) {
        this.selector = selector;
    }

    public SelenideElement getElement() {
        return $x(selector);
    }


    public void applyFilter() {
        Allure.step(this.logging(), () -> {
            this.getElement().shouldBe(visible).click();
        });
    }

    public String logging() {
        return switch (this) {
            case PHONE -> "Фильтруем телефоны";
            case LAPTOP -> "Фильтруем ноутбуки";
            case MONITOR -> "Фильтруем мониторы";
        };
    }

    public static ElementsCollection GetAllItemsOnPage() {
        return item;
    }

    public static List<String> filterItems(ItemsFilter item) {

        Allure.step("Проверяем изначальное количество элементов на странице", () -> {
            initialItems = GetAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();

        });

        item.applyFilter();

        return GetAllItemsOnPage().shouldHave(sizeLessThan(initialItems.size())).texts();

    }

    public static void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {

        assertFalse(filteredItems.isEmpty());

        for (String item : filteredItems) {
            assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Товар '" + item + "' не соответствует списку брендов");
        }
    }
}
