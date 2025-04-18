package selenide.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import selenide.components.Alerts;
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

        Allure.step("Открываем главную страницу и вызываем модальное окно логина", () -> {
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

    public LoginPage login(User user) {
        Allure.step("Заполняем login и password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Подтверждаем логин", () -> {
            confirmButton.click();
        });

        return this;
    }

    public LoginPage wrongLogin(User user, Alerts expectedAlert) {
        login(user);
        Allure.step("Проверяем алерт логина", () -> {
            alertText = Selenide.confirm();
            assertEquals(expectedAlert.getMessage(), alertText);
        });

        return this;
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

}
