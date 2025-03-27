package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private SelenideElement usernameField = $("#sign-username");
    private SelenideElement passwordField = $("#sign-password");
    private SelenideElement confirmButton = $("#signInModal  button.btn.btn-primary");
    MainPage mp = new MainPage();


    public void registration(String login, String pass, String dialogText) {
        mp.waitMainPageToBeLoaded();
        mp.gotoRegistration();
        waitUntilLoaded();
        usernameField.setValue(login);
        passwordField.setValue(pass);
        confirmButton.click();

        Selenide.confirm(dialogText);
        usernameField.shouldNot(visible);

    }

    public void waitUntilLoaded() {
        usernameField.shouldBe(visible);
    }


}
