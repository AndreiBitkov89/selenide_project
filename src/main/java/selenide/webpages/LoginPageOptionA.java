package selenide.webpages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import selenide.BasePage;
import selenide.helpers.AlertTypes;
import selenide.components.NavBarComponent;
import selenide.helpers.CustomAlert;
import selenide.helpers.Decorator;
import selenide.interfaces.LoginPage;
import selenide.valueObject.User;

public class LoginPageOptionA extends BasePage<LoginPageOptionA> implements LoginPage {

    private SelenideElement usernameField = $("#loginusername");
    private SelenideElement passwordField = $("#loginpassword");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='logIn()']"));
    private SelenideElement loginLabel = $("#logInModalLabel");
    private SelenideElement modalWindow = $("#logInModal .modal-body");
    private SelenideElement modalFooter = $("div#logInModal .modal-footer");
    private SelenideElement closeButton = modalFooter.$("button:nth-of-type(1)");
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

    public LoginPageOptionA login(User user) {
        Allure.step("Fill login и password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Accept login", () -> {
            confirmButton.click();
        });

        return this;
    }

    public LoginPageOptionA wrongLogin(User user, AlertTypes expectedAlert) {
        login(user);
        Allure.step("Check alert after login", () -> {
            CustomAlert alert = new CustomAlert(expectedAlert);
            System.out.println(alert.getText());
            alert.accept();
        });

        return this;
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

}
