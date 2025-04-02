package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.LoadableComponent;
import selenide.components.*;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends LoadableComponent {

    private final SelenideElement username = $("#nameofuser");
    private final SelenideElement title = $("a.navbar-brand");
    private final SelenideElement footer = $("div#fotcont");
    private final ElementsCollection item = $$(".card-title a");

    @Override
    public void waitUntilLoaded() {
        this.title.shouldBe(visible);
    }

    public void shouldShowWelcome(String name) {
        Allure.step("Поверяем наличие имени юзера на главной странице после логина", () -> {
            username.shouldBe(visible).shouldHave(text(name));
        });

    }

    public SelenideElement getUsernameAfterLogin() {
        return username;
    }

    public ElementsCollection GetAllItemsOnPage() {
        return item;
    }

    public List<String> filterItems(ItemsFilter item) {
        waitUntilLoaded();

        List<String> initialItems = GetAllItemsOnPage().shouldHave(sizeGreaterThan(0)).texts();

        item.applyFilter();

        return GetAllItemsOnPage().shouldHave(sizeLessThan(initialItems.size())).texts();

    }

    public void assertFilteredItems(List<String> filteredItems, List<String> allowedBrands) {

        assertFalse(filteredItems.isEmpty());

        for (String item : filteredItems) {
            assertTrue(allowedBrands.stream().anyMatch(brand -> item.toLowerCase().contains(brand)), "Товар '" + item + "' не соответствует списку брендов");
        }
    }

    public void gotoItemPage(String title) {
        waitUntilLoaded();
        SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
        titleItem.shouldBe(enabled).click();
    }

    public SelenideElement getFooter() {
        return footer;
    }

}

