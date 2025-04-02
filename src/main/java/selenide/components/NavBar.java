package selenide.components;
import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public enum NavBar {
    CART("#navbarExample #cartur"),
    LOGIN("#login2"),
    SIGN("#signin2");

    private final String selector;

    NavBar(String selector){
        this.selector = selector;
    }

    public SelenideElement getElement() {
        return $(selector);
    }

    public void gotoNavBar() {
        Allure.step(this.logging(), () -> {
            this.getElement().shouldBe(visible).click();
        });
    }

    public String logging() {
        return switch (this) {
            case CART -> "Переход в корзину";
            case LOGIN -> "Переход к логину";
            case SIGN -> "Переход к регистрации";
        };
    }
}
