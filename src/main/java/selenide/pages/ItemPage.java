package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static selenide.pages.MainPage.gotoItemPage;

public class ItemPage extends BasePage<ItemPage> {

    private SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private SelenideElement itemTitle = $("h2.name");
    private SelenideElement itemPrice = $("h3.price-container");
    private final String DIALOG = "Product added";
    private final String title;

    public ItemPage(String title) {
        this.title = title;
    }

    @Override
    public void load() {
        Allure.step("Открываем карточку товара: " + title, () -> {
            gotoItemPage(title);
        });
    }

    @Override
    public void isLoaded() {
        Allure.step("Проверка загрузки страницы", () -> {
            itemTitle.shouldBe(visible);
            itemPrice.shouldBe(visible);
            addToCartButton.shouldBe(visible);
        });
    }

    public void addItemInCart() {
        Allure.step("Добавление товара в корзину", () -> {
            addToCartButton.shouldBe(visible).click();
        });
        Allure.step("Закрываем алерт", () -> {
            Selenide.confirm(DIALOG);
        });
    }

    public String getItemName() {
        return itemTitle.getText();
    }

    public int returnPrice() {

        return Integer.parseInt(itemPrice.text().replace("$", "").trim().split("\\s")[0]);

    }

}
