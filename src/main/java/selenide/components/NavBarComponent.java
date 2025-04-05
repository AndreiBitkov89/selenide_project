package selenide.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NavBarComponent {

    private final SelenideElement CART = $("#cartur");
    private final SelenideElement LOGIN = $("#login2");
    private final SelenideElement SIGNUP = $("#signin2");

    public void goTo(SelenideElement element) {
        Allure.step("Переход к " + element.name(), () -> {
            element.shouldBe(visible).click();
        });
    }


    public SelenideElement getCart() {
        return CART;
    }

    public SelenideElement getLogin() {
        return LOGIN;
    }

    public SelenideElement getSignUp() {
        return SIGNUP;
    }
}
