package pages.itemPage;

import com.codeborne.selenide.*;
import enums.AlertType;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import wrappers.CustomAlert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ItemPage extends BasePage<ItemPage> {

    private final SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private final SelenideElement itemTitle = $("h2.name");
    private final SelenideElement itemPrice = $("h3.price-container");

    public ItemPage(String title) {
    }

    @Override
    public void isLoaded() {
        Allure.step("Check load of the item page", () -> {
            itemTitle.shouldBe(visible);
            itemPrice.shouldBe(visible);
            addToCartButton.shouldBe(visible);
        });
    }

    public void addItemInCart() {
        Allure.step("Add item to cart", () -> addToCartButton.click());
        Allure.step("Confirm alert dialog", () -> new CustomAlert(AlertType.PRODUCT_ADDED).accept());
    }

    public String getItemName() {
        return Allure.step("Get item name", itemTitle::getText);

    }

    public int getPrice() {
        return Integer.parseInt(
                itemPrice.getText()
                        .replace("$", "")
                        .trim()
                        .split("\\s")[0]
        );
    }
}
