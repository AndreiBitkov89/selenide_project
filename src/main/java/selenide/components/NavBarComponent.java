package selenide.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NavBarComponent {

    private final SelenideElement CART = $("#cartur");
    private final SelenideElement LOGIN = $("#login2");
    private final SelenideElement SIGNUP = $("#signin2");

    public void goToCart() {
        Allure.step("Переход в корзину", () ->
                CART.shouldBe(visible).click()
        );
    }

    public void goToLogin() {
        Allure.step("Переход к логину", () ->
                LOGIN.shouldBe(visible).click()
        );
    }

    public void goToSignUp() {
        Allure.step("Переход к регистрации", () ->
                SIGNUP.shouldBe(visible).click()
        );
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
