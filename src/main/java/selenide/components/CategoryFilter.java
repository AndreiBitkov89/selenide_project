package selenide.components;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;


public class CategoryFilter {

    private final SelenideElement PHONE = $(By.xpath("//*[@class ='list-group']//*[text()='Phones']"));
    private final SelenideElement LAPTOP = $(By.xpath("//*[@class ='list-group']//*[text()='Laptops']"));
    private final SelenideElement MONITOR = $(By.xpath("//*[@class ='list-group']//*[text()='Monitors']"));
    private List<String> initialItems;
    private static final ElementsCollection ITEMS = $$(".card-title a");

    public SelenideElement getPHONE() {
        return PHONE;
    }

    public SelenideElement getLAPTOP() {
        return LAPTOP;
    }

    public SelenideElement getMONITOR() {
        return MONITOR;
    }

    public static ElementsCollection getAllItemsOnPage() {
        return ITEMS;
    }

    public List<String> filterAndReturnItems(SelenideElement element) {

        Allure.step("Check initial number of items in page", () -> {
            initialItems = getAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();
        });

        element.shouldBe(visible).click();
        getAllItemsOnPage().shouldBe(sizeLessThan(initialItems.size()), Duration.ofSeconds(5)).shouldHave(sizeGreaterThan(0));

        return getAllItemsOnPage().texts();
    }

    public void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {
        Allure.step("Check items in page after applying filter", () -> {
            assertFalse(filteredItems.isEmpty());

            for (String item : filteredItems) {
                assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Item '" + item + "' don't match the list of brands");
            }

        });

    }

}
