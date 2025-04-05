package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import selenide.BasePage;
import selenide.components.NavBarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage<CartPage> {
    private final SelenideElement title = $(By.xpath("//h2[text()='Products']"));
    private NavBarComponent navBarComponent = new NavBarComponent();

    public SelenideElement getItemInCart(String title) {
        return $(By.xpath("//td[text()='" + title + "']"));
    }

    public int getPriceOfItemInCart(String title) {
        SelenideElement item = $(By.xpath("//td[text()='" + title + "']/following-sibling::td[1]"));
        return Integer.parseInt(item.text().trim());
    }

    @Override
    protected void load() {
        navBarComponent.goTo(navBarComponent.getCart());
    }

    @Override
    protected void isLoaded() {
        title.shouldBe(visible);
    }
}
