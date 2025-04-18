package selenide.components;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;


public class CategoryFilter {
    @Getter
    private final SelenideElement PHONE = $(By.xpath("//*[@class ='list-group']//*[text()='Phones']"));
    @Getter
    private final SelenideElement LAPTOP = $(By.xpath("//*[@class ='list-group']//*[text()='Laptops']"));
    @Getter
    private final SelenideElement MONITOR = $(By.xpath("//*[@class ='list-group']//*[text()='Monitors']"));

    private List<String> initialItems;
    private static final ElementsCollection ITEMS = $$(".card-title a");

    public static ElementsCollection getAllItemsOnPage() {
        return ITEMS;
    }

    public List<String> filterAndReturnItems(SelenideElement element) {

        Allure.step("Проверяем изначальное количество элементов на странице", () -> {
            initialItems = getAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();
        });

        element.shouldBe(visible).click();
        getAllItemsOnPage().shouldBe(sizeLessThan(initialItems.size()), Duration.ofSeconds(5)).shouldHave(sizeGreaterThan(0));

        return getAllItemsOnPage().texts();
    }

    public void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {

        assertFalse(filteredItems.isEmpty());

        for (String item : filteredItems) {
            assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Товар '" + item + "' не соответствует списку брендов");
        }
    }

}
