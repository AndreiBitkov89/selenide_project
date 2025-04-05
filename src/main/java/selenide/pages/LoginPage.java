package selenide.pages;

import com.codeborne.selenide.*;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import selenide.BasePage;
import selenide.components.Alerts;
import selenide.components.BaseModalWindow;
import selenide.components.NavBarComponent;
import selenide.helpers.User;

public class LoginPage extends BasePage<LoginPage> implements BaseModalWindow {

    private SelenideElement usernameField = $("#loginusername");
    private SelenideElement passwordField = $("#loginpassword");
    private SelenideElement confirmButton = $(By.xpath("//*[@onclick='logIn()']"));
    private SelenideElement loginLabel = $("#logInModalLabel");
    private SelenideElement modalWindow = $("#logInModal .modal-body");

    private String alertText;
    private NavBarComponent navBarComponent = new NavBarComponent();

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

        }

    public void login(User user) {

        Allure.step("Заполняем login и password", () -> {
            usernameField.shouldBe(visible).setValue(user.getUsername());
            passwordField.shouldBe(visible).setValue(user.getPassword());

        });

        Allure.step("Подтверждаем логин", () -> {
            confirmButton.click();
        });
    }

    public void wrongLogin(User user, Alerts expectedAlert) {
        login(user);
        Allure.step("Проверяем алерт логина", () -> {
            alertText = Selenide.confirm();
            assertEquals(alertText, expectedAlert.getMessage());
        });
    }

    public SelenideElement getModal() {
        return modalWindow;
    }

    public Cookie getCookie(String cookie) {
        for (int i = 0; i < 10; i++) {
            if (WebDriverRunner.getWebDriver().manage().getCookieNamed(cookie) != null) {
                return WebDriverRunner.getWebDriver().manage().getCookieNamed(cookie);
            }
            Selenide.sleep(100);
        }
        return null;
    }
}
