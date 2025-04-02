package selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import selenide.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends LoadableComponent {
    private final SelenideElement title = $(By.xpath("//h2[text()='Products']"));
    private final SelenideElement itemBlock = $("tbody#tbodyid");

    @Override
    protected void waitUntilLoaded() {
        title.shouldBe(visible);
    }

    public SelenideElement getItemInCart(String title) {
        waitUntilLoaded();
        return $(By.xpath("//td[text()='" + title + "']"));
    }

    public int getPriceOfItemInCart(String title) {
        SelenideElement item = $(By.xpath("//td[text()='" + title + "']/following-sibling::td[1]"));
        return Integer.parseInt(item.text().trim());
    }
}
