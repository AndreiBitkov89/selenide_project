package pages.commonComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class NavBarComponent {

    private final SelenideElement cart = $(By.xpath("//*[text()='Cart']"));
    private final SelenideElement login = $("#login2");
    private final SelenideElement logout = $(By.xpath("//*[text()='Log out']"));
    private final SelenideElement signup = $("#signin2");
    private final SelenideElement usernameLogged = $("#nameofuser");

    public void clickLogin() {
        Allure.step("Click login", () -> login.shouldBe(visible).click());
    }

    public void clickCart() {
        Allure.step("Click cart", () -> cart.shouldBe(visible).click());
    }

    public void clickSignUp() {
        Allure.step("Click signup", () -> signup.shouldBe(visible).click());
    }

    public NavBarComponent clickLogout() {
        Allure.step("Click signup", () -> logout.shouldBe(visible).click());

        return this;
    }

    public boolean shouldShowUserName(boolean displayed) {
        if(displayed){
            return usernameLogged.shouldBe(visible).isDisplayed();
        } else {
            return usernameLogged.shouldBe(hidden).isDisplayed();
        }

    }

}
