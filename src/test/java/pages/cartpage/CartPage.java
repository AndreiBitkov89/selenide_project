package pages.cartpage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import pages.commonComponents.NavBarComponent;
import org.openqa.selenium.By;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage<CartPage> {
    //todo точно готовы инициализировать в полях класса?
    //todo верхний регистр
    private final NavBarComponent NAVBAR_COMPONENT = new NavBarComponent();
    private final SelenideElement TITLE = $(By.xpath("//h2[text()='Products']"));
    private final SelenideElement TOTAL_PRICE = $("#totalp");
    //todo почему тут static final, а выше нет?)
    private static final SelenideElement MAKE_ORDER_BUTTON = $("button[data-target='#orderModal']");

    @Override
    public void load() {
        Allure.step("Go to card", () -> NAVBAR_COMPONENT.goTo(NAVBAR_COMPONENT.getCart())
        );
    }

    @Override
    public void isLoaded() {
        Allure.step("Check that card is opened", () -> TITLE.shouldBe(visible, Duration.ofSeconds(3))
        );
    }

    //todo я вижу метод получения SelenideElement?? Не могу поверить своим глазам
    public SelenideElement getItemInCart(String title) {
        //todo локатор можно вынести в переменные, тут только добавлять тайтл
        return $(By.xpath("//td[text()='" + title + "']"));
    }

    public int getPriceOfItemInCart(String title) {
        return Allure.step("Get price of item in cart", () -> {
            //todo локатор можно вынести в переменные, тут только добавлять тайтл
            SelenideElement item = $(By.xpath("//td[text()='" + title + "']/following-sibling::td[1]"));
            return Integer.parseInt(item.text().trim());
        });

    }

    public int getTotalPrice() {
        return Allure.step("Get total price of items in cart", () -> {
            TOTAL_PRICE.shouldBe(visible);
            return Integer.parseInt(TOTAL_PRICE.text());
        });
    }

    //todo странное название, и что будет, если не дождется, кажется, усложненная реализация
    public int waitUntilTotalPriceEquals(int expectedSum) {
        return Allure.step("Wait change of price", () -> {
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
        });

    }

    public CartPage deleteItemFromCart(String title) {
        return Allure.step("Delete item in cart", () -> {
            //todo почему ждем видимости одного элемента, а кликаем на другой?
            getItemInCart(title).shouldBe(visible);
            //todo локатор бы в переменную, хотя бы локальную
            $(By.xpath("//td[text()='" + title + "']/following-sibling::td/a")).click();
            return this;
        });
    }

    //todo почему он должен быть static?
    public static void goToOrder() {
        Allure.step("Go to order page", () -> {
            MAKE_ORDER_BUTTON.shouldBe(visible).click();
        });
    }

}
