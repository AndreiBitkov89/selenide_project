package pages.itempage;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.mainpage.MainPage.gotoItemPage;

public class ItemPage extends BasePage<ItemPage> {

    private final SelenideElement addToCartButton = $(By.xpath("//a[text()='Add to cart']"));
    private final SelenideElement itemTitle = $("h2.name");
    private final SelenideElement itemPrice = $("h3.price-container");

    private static final String DIALOG = "Product added";
    private final String pageTitle;

    public ItemPage(String title) {
        this.pageTitle = title;
    }

    @Override
    public void load() {
        Allure.step("Open page of the item: " + pageTitle, () -> gotoItemPage(pageTitle));
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
        Allure.step("Add item to cart", () -> addToCartButton.shouldBe(visible).click());
        Allure.step("Confirm alert dialog", () -> Selenide.confirm(DIALOG));
    }

    public String getItemName() {
        return itemTitle.getText();
    }

    public int returnPrice() {
        return Integer.parseInt(
                itemPrice.getText()
                        .replace("$", "")
                        .trim()
                        .split("\\s")[0]
        );
    }
}
