package selenide.components;

import com.codeborne.selenide.*;
import io.qameta.allure.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

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


    public List<String> filterAndReturnItems() {

        Allure.step("Проверяем изначальное количество элементов на странице", () -> {
            initialItems = GetAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();
        });

        Allure.step(logging(), ()-> {
            this.getElement().shouldBe(visible).click();
        });

        return GetAllItemsOnPage().shouldHave(sizeLessThan(initialItems.size())).texts();

    }

    public static void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {

        assertFalse(filteredItems.isEmpty());

        for (String item : filteredItems) {
            assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Товар '" + item + "' не соответствует списку брендов");
        }
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

}
