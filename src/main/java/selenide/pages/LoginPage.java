package selenide.pages;

import com.codeborne.selenide.*;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import selenide.LoadableComponent;
import selenide.components.Alerts;

public class LoginPage extends LoadableComponent {

    private SelenideElement usernameField = $("#loginusername");
    private SelenideElement passwordField = $("#loginpassword");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='logIn()']"));
    private SelenideElement loginLabel = $("#logInModalLabel");
    private SelenideElement modalWindow = $("#logInModal .modal-body");
    private String alertText;
    private Cookie waitCookie;

    public void waitUntilLoaded() {
        loginLabel.shouldBe(visible, Duration.ofSeconds(3));
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        confirmButton.shouldBe(visible);
    }

    public void login(String login, String pass) {

        waitUntilLoaded();
        Allure.step("Заполняем login и password", () -> {
            usernameField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(login);
            passwordField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(pass);
        });

        Allure.step("Подтверждаем логин", () -> {
            confirmButton.click();
        });
    }

    public void fakeLogin(String login, String pass, Alerts expectedAlert) {
        login(login, pass);
        Allure.step("Проверяем алерт логина", () -> {
            alertText = Selenide.confirm();
            assertEquals(alertText, expectedAlert.getMessage());
        });
    }


    public SelenideElement getModal() {
        waitUntilLoaded();
        return modalWindow;
    }

    public Cookie getCookie(String cookie) {
        for (int i = 0; i < 10; i++) {
            waitCookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookie);
            if (waitCookie != null) {
                return waitCookie;
            }
            Selenide.sleep(100);
        }
        return null;
    }
}
