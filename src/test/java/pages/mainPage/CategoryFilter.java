package pages.mainPage;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryFilter {

    private final SelenideElement filter = $(".list-group");
    private final MainPage mainPage;


    public CategoryFilter(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public SelenideElement getCategory(String category) {
        return filter.$(Selectors.byText(category));
    }

    public List<ProductCardElement> filterAndReturnProductElements(String category) {
        SelenideElement categoryElement = getCategory(category);

        Allure.step("Click on filter and wait for page update", () -> {
            int initialSize = mainPage.getAllProducts().size();
            categoryElement.shouldBe(visible).click();
            //todo проверяешь изменение размера после клика, а его в некоторых кейсах может не быть?
            // - на этом окружении больше не за что зацепиться, иначе долгий рендергинг приводит к падению,
            // без sleep не понял как решить. По сути согласен
            $$(".card").shouldHave(sizeNotEqual(initialSize));
        });

        return mainPage.getAllProducts();
    }

    public List<String> getTitles(List<ProductCardElement> products) {
        return Allure.step("Return titles of displayed items", () ->
                products.stream()
                        .map(ProductCardElement::getTitle)
                        .toList()
        );
    }

    public void assertFilteredItems(List<String> filteredTitles, List<String> allowedBrands) {
        Allure.step("Check items in page after applying filter", () -> {
            assertFalse(filteredTitles.isEmpty(), "List of items is empty");

            List<String> allowedLower = allowedBrands.stream()
                    .map(String::toLowerCase)
                    .toList();

            for (String title : filteredTitles) {
                boolean match = allowedLower.stream().anyMatch(title.toLowerCase()::contains);
                assertTrue(match, "Item '" + title + "' doesn't match allowed brands");
            }

        });
    }

}
