package pages.cart;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.commonComponents.NavBarComponent;
import org.openqa.selenium.By;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage<CartPage> {
    private NavBarComponent navBarComponent = new NavBarComponent();
    private SelenideElement title = $(By.xpath("//h2[text()='Products']"));
    private SelenideElement totalPrice = $("#totalp");
    private static SelenideElement orderButton = $("button[data-target='#orderModal']");

    @Override
    public void load() {
        navBarComponent.goTo(navBarComponent.getCart());
    }
    @Override
    public void isLoaded() {
        title.shouldBe(visible, Duration.ofSeconds(3));
    }

    public SelenideElement getItemInCart(String title) {
        return $(By.xpath("//td[text()='" + title + "']"));
    }

    public int getPriceOfItemInCart(String title) {
        SelenideElement item = $(By.xpath("//td[text()='" + title + "']/following-sibling::td[1]"));
        return Integer.parseInt(item.text().trim());
    }

    public int getTotalPrice(){
        totalPrice.shouldBe(visible);
        return Integer.parseInt(totalPrice.text());
    }

    public int waitUntilTotalPriceEquals(int expectedSum) {
        totalPrice.shouldBe(visible);
        Selenide.Wait().until(driver -> {
            String raw = totalPrice.text().trim();
            try {
                return Integer.parseInt(raw) == expectedSum;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return Integer.parseInt(totalPrice.text());
    }

    public CartPage deleteItemFromCart(String title) {
        getItemInCart(title).shouldBe(visible);
        $(By.xpath("//td[text()='" + title + "']/following-sibling::td/a")).click();
        return this;
    }

    public static void goToOrder(){
        orderButton.shouldBe(visible).click();
    }

}
