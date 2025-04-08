package selenide.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import selenide.BasePage;
import selenide.components.Alerts;
import selenide.components.NavBarComponent;
import selenide.helpers.SlowType;
import selenide.helpers.User;

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
    private SlowType slowType = new SlowType();

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

    public void login(User user) {
        Allure.step("Заполняем login и password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Подтверждаем логин", () -> {
            confirmButton.click();
        });
    }

    public void wrongLogin(User user, Alerts expectedAlert) {
        login(user);
        Allure.step("Проверяем алерт логина", () -> {
            alertText = Selenide.confirm();
            assertEquals(expectedAlert.getMessage(), alertText);
        });
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

}
