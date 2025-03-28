package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends LoadableComponent {

    private final SelenideElement usernameField = $("#loginusername");
    private final SelenideElement passwordField = $("#loginpassword");
    private final SelenideElement confirmButton = $("#logInModal  button.btn.btn-primary");
    private final SelenideElement loginLabel = $("#logInModalLabel");
    MainPage mp = new MainPage();


    public void login(String login, String pass)  {
        mp.waitUntilLoaded();
        mp.gotoLogin();
        waitUntilLoaded();
        usernameField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(login);
        usernameField.shouldHave(value(login));
        passwordField.shouldBe(enabled, Duration.ofSeconds(3)).setValue(pass);
        passwordField.shouldHave(value(pass));
        confirmButton.click();

    }

    public void fakeLogin(String login, String pass, String dialogMessage)  {
        login(login, pass);
        Selenide.confirm(dialogMessage);
    }

    public void waitUntilLoaded() {
        loginLabel.shouldBe(visible, Duration.ofSeconds(3));

    }

}
