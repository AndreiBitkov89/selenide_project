package pages.cartPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage<CartPage> {

    private static final String itemTitleLocator = "//td[text()='%s']";
    private static final String itemPriceLocator = "//td[text()='%s']/following-sibling::td[1]";
    private static final String itemDeleteButtonLocator = "//td[text()='%s']/following-sibling::td/a";

    private final SelenideElement title = $x("//h2[text()='Products']");
    private final SelenideElement totalPrice = $("#totalp");
    private final SelenideElement makeOrderButton = $("button[data-target='#orderModal']");

    @Override
    public void isLoaded() {
        Allure.step("Check that card is opened", () -> title.shouldBe(visible)
        );
    }

    public boolean isItemInCart(String itemTitle, boolean exist) {
        SelenideElement item = $x(String.format(itemTitleLocator, itemTitle));
        if (exist) {
            return item.shouldBe(visible).exists();
        } else {
            return item.shouldBe(hidden).exists();
        }

    }

    public int getPriceOfItemInCart(String itemTitle) {
        return Allure.step("Get price of item in cart", () -> {
            SelenideElement item = $x(String.format(itemPriceLocator, itemTitle));
            item.shouldBe(visible);
            return Integer.parseInt(item.text().trim());
        });
    }

    public boolean checkTotalPrice(int expectedSum) {
        return Allure.step("Wait for total price to become " + expectedSum, () -> {
            totalPrice.shouldBe(visible);
            Wait().until(driver -> {
                try {
                    return Integer.parseInt(totalPrice.text().trim()) == expectedSum;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
            return true;
        });
    }

    public void deleteItemFromCart(String itemTitle) {
        Allure.step("Delete item from cart: " + itemTitle, () -> {
            SelenideElement deleteButton = $x(String.format(itemDeleteButtonLocator, itemTitle));
            deleteButton.shouldBe(visible).click();
        });
    }

    public CartPage goToOrder() {
        return Allure.step("Go to order page", () -> {
            makeOrderButton.shouldBe(visible).click();
            return this;
        });
    }

}
