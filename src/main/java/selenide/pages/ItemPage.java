package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.LoadableComponent;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ItemPage extends LoadableComponent {

    private SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private SelenideElement itemTitle = $("h2.name");
    private SelenideElement itemPrice = $("h3.price-container");
    private final String DIALOG = "Product added";

    @Override
    public void waitUntilLoaded() {
        itemTitle.shouldBe(visible);
    }

    public void addItemToCart() {
        this.waitUntilLoaded();

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
