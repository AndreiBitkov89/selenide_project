package pages.mainpage;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import pages.PageManager;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryFilter {

    private final SelenideElement FILTER = $(".list-group");
    private final SelenideElement PHONE = FILTER.$(Selectors.byText("Phones"));
    private final SelenideElement LAPTOP = FILTER.$(Selectors.byText("Laptops"));
    private final SelenideElement MONITOR = FILTER.$(Selectors.byText("Monitors"));
    private MainPage mainPage = PageManager.mainPage();
    private int initialSize;

    public SelenideElement getPhone() {
        return PHONE;
    }

    public SelenideElement getLaptop() {
        return LAPTOP;
    }

    public SelenideElement getMonitor() {
        return MONITOR;
    }

    public List<ProductCardElement> filterAndReturnProductElements(SelenideElement categoryElement) {
        initialSize = mainPage.getAllProducts().size();

        Allure.step("Click on filter and wait for page update", () -> {
            categoryElement.shouldBe(visible).click();

            $$(".card").shouldHave(sizeNotEqual(initialSize));
        });

        return mainPage.getAllProducts();
    }


    public List<String> extractTitles(List<ProductCardElement> products) {
        return products.stream()
                .map(ProductCardElement::getTitle)
                .toList();
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
