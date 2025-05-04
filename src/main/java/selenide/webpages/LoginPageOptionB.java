package selenide.webpages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import selenide.BasePage;
import selenide.components.NavBarComponent;
import selenide.helpers.AlertTypes;
import selenide.helpers.CustomAlert;
import selenide.helpers.Decorator;
import selenide.interfaces.LoginPage;
import selenide.valueObject.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageOptionB extends BasePage<LoginPageOptionB> implements LoginPage {

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

    public LoginPageOptionB login(User user) {
        Allure.step("Fill login и password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Accept login", () -> {
            confirmButton.click();
        });

        return this;
    }


    public SelenideElement getModal() {
        return modalWindow;
    }

}
