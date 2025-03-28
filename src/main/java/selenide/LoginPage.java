package selenide;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends LoadableComponent {

    private final SelenideElement usernameField = $("#loginusername");
    private final SelenideElement passwordField = $("#loginpassword");
    private final SelenideElement confirmButton = $("#logInModal  button.btn.btn-primary");
    MainPage mp = new MainPage();

    public void login(String login, String pass) {
        mp.waitUntilLoaded();
        mp.gotoLogin();
        waitUntilLoaded();
        usernameField.setValue(login);
        passwordField.setValue(pass);
        confirmButton.click();
    }

    public void waitUntilLoaded() {
        usernameField.shouldBe(visible, Duration.ofSeconds(3));
        usernameField.shouldBe(enabled, Duration.ofSeconds(3));
        passwordField.shouldBe(visible, Duration.ofSeconds(3));
        passwordField.shouldBe(enabled, Duration.ofSeconds(3));
    }

}
