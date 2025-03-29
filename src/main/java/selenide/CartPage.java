package selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends LoadableComponent {
    private final SelenideElement title = $(By.xpath("//h2[text()='Products']"));
    private final SelenideElement itemInCart = $("tbody#tbodyid");
    private final SelenideElement itemNameInCart = itemInCart.$("td:nth-child(2)");


    @Override
    protected void waitUntilLoaded() {
        title.shouldBe(visible);
    }

    public String getItemName() {
        waitUntilLoaded();
        itemInCart.shouldBe(visible);
        return itemNameInCart.text();
    }
}
