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
    private final NavBarComponent NAVBAR_COMPONENT = new NavBarComponent();
    private final SelenideElement TITLE = $(By.xpath("//h2[text()='Products']"));
    private final SelenideElement TOTAL_PRICE = $("#totalp");
    private static final SelenideElement MAKE_ORDER_BUTTON = $("button[data-target='#orderModal']");

    @Override
    public void load() {
        NAVBAR_COMPONENT.goTo(NAVBAR_COMPONENT.getCart());
    }
    @Override
    public void isLoaded() {
        TITLE.shouldBe(visible, Duration.ofSeconds(3));
    }

    public SelenideElement getItemInCart(String title) {
        return $(By.xpath("//td[text()='" + title + "']"));
    }

    public int getPriceOfItemInCart(String title) {
        SelenideElement item = $(By.xpath("//td[text()='" + title + "']/following-sibling::td[1]"));
        return Integer.parseInt(item.text().trim());
    }

    public int getTOTAL_PRICE(){
        TOTAL_PRICE.shouldBe(visible);
        return Integer.parseInt(TOTAL_PRICE.text());
    }

    public int waitUntilTotalPriceEquals(int expectedSum) {
        TOTAL_PRICE.shouldBe(visible);
        Selenide.Wait().until(driver -> {
            String raw = TOTAL_PRICE.text().trim();
            try {
                return Integer.parseInt(raw) == expectedSum;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        return Integer.parseInt(TOTAL_PRICE.text());
    }

    public CartPage deleteItemFromCart(String title) {
        getItemInCart(title).shouldBe(visible);
        $(By.xpath("//td[text()='" + title + "']/following-sibling::td/a")).click();
        return this;
    }

    public static void goToOrder(){
        MAKE_ORDER_BUTTON.shouldBe(visible).click();
    }

}
