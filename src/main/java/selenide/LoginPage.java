package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement usernameField = $("#loginusername");
    private SelenideElement passwordField = $("#loginpassword");
    private SelenideElement confirmButton = $("#logInModal  button.btn.btn-primary");
    MainPage mp = new MainPage();

    public void login(String login, String pass) {
        mp.waitMainPageToBeLoaded();
        mp.gotoLogin();
        waitUntilLoaded();
        usernameField.setValue(login);
        passwordField.setValue(pass);
        confirmButton.click();
    }

    public void waitUntilLoaded() {
        usernameField.shouldBe(visible);
    }



}
