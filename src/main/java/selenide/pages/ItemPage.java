package selenide.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ItemPage extends LoadableComponent {

    private final SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private final SelenideElement itemTitle = $("h2.name");

    public void addItemToCart() {
        this.waitUntilLoaded();

        Allure.step("Добавление товара в корзину", () -> {
            addToCartButton.shouldBe(visible).shouldBe(enabled).click();
        });
        Allure.step("Закрываем алерт", () -> {
            Selenide.confirm("Product added");
        });
    }


    @Override
    protected void waitUntilLoaded() {
        itemTitle.shouldBe(visible);
    }

    public String getItemName() {
        return itemTitle.getText();

    }
}
