package selenide.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import io.qameta.allure.Allure;
import selenide.LoadableComponent;
import selenide.utilsAndHelpers.NavBar;

public class LoginPage extends LoadableComponent {

    private final SelenideElement usernameField = $("#loginusername");
    private final SelenideElement passwordField = $("#loginpassword");
    private final SelenideElement confirmButton = $("#logInModal  button.btn.btn-primary");
    private final SelenideElement loginLabel = $("#logInModalLabel");
    private final SelenideElement modalWindow = $("#logInModal .modal-body");
    MainPage mp = new MainPage();


    public void login(String login, String pass) {
        mp.waitUntilLoaded();
        Allure.step("Переходим на страницу логина", () -> {
            mp.gotoNavBar(NavBar.LOGIN);
        });

        waitUntilLoaded();
        sleep(300);
        Allure.step("Заполняем login и password", () -> {
            usernameField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(login);
            passwordField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(pass);
        });

        Allure.step("Подтверждаем логин", () -> {
            confirmButton.click();
        });

    }


    public void fakeLogin(String login, String pass, String dialogMessage) {
        login(login, pass);
        Allure.step("Закрываем алерт логин", () -> {
            Selenide.confirm(dialogMessage);
        });

    }

    public void waitUntilLoaded() {
        loginLabel.shouldBe(visible, Duration.ofSeconds(3));
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);
        confirmButton.shouldBe(enabled);

    }

    public SelenideElement getModal() {
        return modalWindow;
    }

}
