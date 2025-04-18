package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import selenide.components.NavBarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage<CartPage> {
    private NavBarComponent navBarComponent = new NavBarComponent();

    private SelenideElement title = $(By.xpath("//h2[text()='Products']"));
    private SelenideElement totalPrice = $("#totalp");
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

    public CartPage deleteItemFromCart(String title) {
        getItemInCart(title).shouldBe(visible);
        $(By.xpath("//td[text()='" + title + "']/following-sibling::td/a")).click();
        return this;
    }

    @Override
    public void load() {
        navBarComponent.goTo(navBarComponent.getCart());
    }

    @Override
    public void isLoaded() {
        title.shouldBe(visible);
    }
}
