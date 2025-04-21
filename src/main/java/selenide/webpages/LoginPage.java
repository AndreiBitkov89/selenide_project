package selenide.webpages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import selenide.BasePage;
import selenide.helpers.Alerts;
import selenide.components.NavBarComponent;
import selenide.helpers.Decorator;
import selenide.valueObject.User;

public class LoginPage extends BasePage<LoginPage> implements selenide.interfaces.LoginPage {

    private SelenideElement usernameField = $("#loginusername");
    private SelenideElement passwordField = $("#loginpassword");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='logIn()']"));
    private SelenideElement loginLabel = $("#logInModalLabel");
    private SelenideElement modalWindow = $("#logInModal .modal-body");
    private SelenideElement modalFooter = $("div#logInModal .modal-footer");
    private SelenideElement closeButton = modalFooter.$("button:nth-of-type(1)");

    private String alertText;
    private NavBarComponent navBarComponent = new NavBarComponent();
    private Decorator slowType = new Decorator();

    @Override
    protected void load() {

        Allure.step("Open main page and navigate to login modal", () -> {
            navBarComponent.goTo(navBarComponent.getLogin());
        });
    }

    @Override
    protected void isLoaded() {

        loginLabel.shouldBe(visible);
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        closeButton.shouldHave(visible);

    }

    @Step("Test Step")
    public LoginPage login(User user) {
        Allure.step("Fill login и password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Accept login", () -> {
            confirmButton.click();
        });

        return this;
    }

    public LoginPage wrongLogin(User user, Alerts expectedAlert) {
        login(user);
        Allure.step("Check alert after login", () -> {
            alertText = Selenide.confirm();
            assertEquals(expectedAlert.getMessage(), alertText);
        });

        return this;
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

}
