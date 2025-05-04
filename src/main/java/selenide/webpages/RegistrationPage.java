package selenide.webpages;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import selenide.BasePage;
import selenide.helpers.AlertTypes;
import selenide.components.NavBarComponent;
import selenide.helpers.CustomAlert;
import selenide.helpers.Decorator;
import selenide.valueObject.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage extends BasePage<RegistrationPage> implements selenide.interfaces.RegistrationPage {
    private SelenideElement usernameField = $("input#sign-username");
    private SelenideElement passwordField = $("input#sign-password");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='register()']"));
    private SelenideElement modalWindow = $("#signInModal .modal-content");
    private SelenideElement signupLabel = $("#signInModalLabel");
    private NavBarComponent navBarComponent = new NavBarComponent();
    private Decorator slowType = new Decorator();

    @Override
    public void load() {
        navBarComponent.goTo(navBarComponent.getSignUp());
    }

    @Override
    public void isLoaded() {
        signupLabel.shouldBe(visible, Duration.ofSeconds(5));
        usernameField.shouldBe(visible, Duration.ofSeconds(5));
        passwordField.shouldBe(visible, Duration.ofSeconds(5));
        confirmButton.shouldBe(visible, Duration.ofSeconds(5));
    }

    public RegistrationPage registration(User user, AlertTypes expectedAlert) {
        Allure.step("Fill login and password", () -> {
            slowType.slowType(usernameField, user.getUsername());
            slowType.slowType(passwordField, user.getPassword());
        });

        Allure.step("Accept registration", () -> {
            confirmButton.click();
        });

        Allure.step("Get alert after registration", () -> {
            String dialog = Selenide.confirm();
            assertEquals(expectedAlert.getMessage(), dialog);
        });

        return this;
    }

    public SelenideElement getModal() {
        return modalWindow;
    }
}