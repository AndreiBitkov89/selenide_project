package selenide.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryFilterComponent {
    private final SelenideElement PHONE = $(By.xpath("//*[@class ='list-group']//*[text()='Phones']"));
    private final SelenideElement LAPTOP = $(By.xpath("//*[@class ='list-group']//*[text()='Laptops']"));
    private final SelenideElement MONITOR = $(By.xpath("//*[@class ='list-group']//*[text()='Monitors']"));

    private List<String> initialItems;
    private static final ElementsCollection ITEMS = $$(".card-title a");

    public static ElementsCollection GetAllItemsOnPage() {
        return ITEMS;
    }

    public List<String> filterAndReturnItems(SelenideElement element) {

        Allure.step("Проверяем изначальное количество элементов на странице", () -> {
            initialItems = GetAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();
        });

        element.shouldBe(visible).click();

        return GetAllItemsOnPage().shouldHave(sizeLessThan(initialItems.size())).texts();
    }

    public static void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {

        assertFalse(filteredItems.isEmpty());

        for (String item : filteredItems) {
            assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Товар '" + item + "' не соответствует списку брендов");
        }
    }

    public List<String> filterPhones() {
        return filterAndReturnItems(PHONE);
    }

    public List<String> filterLaptops() {
        return filterAndReturnItems(LAPTOP);
    }

    public List<String> filterMonitors() {
        return filterAndReturnItems(MONITOR);
    }


}
