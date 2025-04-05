package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import selenide.BasePage;
import selenide.components.Alerts;
import selenide.components.BaseModalWindow;
import selenide.components.NavBarComponent;
import selenide.helpers.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage extends BasePage<RegistrationPage> implements BaseModalWindow {
    private SelenideElement usernameField = $("input#sign-username");
    private SelenideElement passwordField = $("input#sign-password");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='register()']"));
    private SelenideElement modalWindow = $("#signInModal .modal-content");
    private SelenideElement signupLabel = $("#signInModalLabel");
    private NavBarComponent navBarComponent = new NavBarComponent();

    public void registration(User user, Alerts alert) {

        Allure.step("Заполняем логин и пароль", () -> {
            usernameField.setValue(user.getUsername())
                    .shouldHave(value(user.getUsername()), Duration.ofSeconds(3));

            passwordField.setValue(user.getPassword())
                    .shouldHave(value(user.getPassword()), Duration.ofSeconds(3));
        });

        Allure.step("Подтверждаем регистрацию", () -> {
            confirmButton.click();
        });

        Allure.step("Получаем алерт о регистрации", () -> {
            String dialog = Selenide.confirm();
            assertEquals(dialog, alert.getMessage());
        });
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

    @Override
    public void load() {
        navBarComponent.goTo(navBarComponent.getSignUp());
    }

    @Override
    public void isLoaded() {
        signupLabel.shouldBe(visible, Duration.ofSeconds(5));
        usernameField.shouldBe(visible,Duration.ofSeconds(5));
        passwordField.shouldBe(visible, Duration.ofSeconds(5));
    }
}
