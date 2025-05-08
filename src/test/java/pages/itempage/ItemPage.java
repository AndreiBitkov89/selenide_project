package pages.itempage;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.mainpage.MainPage.gotoItemPage;

public class ItemPage extends BasePage<ItemPage> {

    private SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private SelenideElement itemTitle = $("h2.name");
    private SelenideElement itemPrice = $("h3.price-container");

    private final String DIALOG = "Product added";
    private String title;

    public ItemPage(String title) {
        this.title = title;
    }

    @Override
    public void load() {
        Allure.step("Open page of the item: " + title, () -> {
            gotoItemPage(title);
        });
    }

    @Override
    public void isLoaded() {
        Allure.step("Check load of the page", () -> {
            itemTitle.shouldBe(visible);
            itemPrice.shouldBe(visible);
            addToCartButton.shouldBe(visible);
        });
    }

    public void addItemInCart() {
        Allure.step("Add item to cart", () -> {
            addToCartButton.shouldBe(visible).click();
        });
        Allure.step("Close alert", () -> {
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
