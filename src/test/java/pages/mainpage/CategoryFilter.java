package pages.mainpage;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import pages.PageManager;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import static pages.mainpage.MainPage.getAllProducts;

public class CategoryFilter {

    private final SelenideElement FILTER = $(".list-group");
    public SelenideElement getCategory(String category) {
        return FILTER.$(Selectors.byText(category));
    }

    public List<ProductCardElement> filterAndReturnProductElements(SelenideElement categoryElement) {
        Allure.step("Click on filter and wait for page update", () -> {
            int initialSize = getAllProducts().size();
            categoryElement.shouldBe(visible).click();
            $$(".card").shouldHave(sizeNotEqual(initialSize));
        });

        return getAllProducts();
    }

    public List<String> extractTitles(List<ProductCardElement> products) {
        return Allure.step("Return titles of displayed items", () ->
                products.stream()
                        .map(ProductCardElement::getTitle)
                        .toList()
        );
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
