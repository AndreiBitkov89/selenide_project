package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private SelenideElement usernameField = $("#sign-username");
    private SelenideElement passwordField = $("#sign-password");
    private SelenideElement confirmButton = $("#signInModal  button.btn.btn-primary");
    private SelenideElement modalWindow = $("#signInModal .modal-body");
    private  SelenideElement closeButton = $(By.xpath("//*[@id='signInModal']//ancestor::button[text()='Close']"));
    MainPage mp = new MainPage();


    public void registration(String login, String pass, String dialogText) {
        mp.waitMainPageToBeLoaded();
        mp.gotoRegistration();
        waitUntilLoaded();
        usernameField.setValue(login);
        passwordField.setValue(pass);
        confirmButton.click();

        Selenide.confirm(dialogText);

    }

    public void waitUntilLoaded() {
        usernameField.shouldBe(visible, Duration.ofSeconds(3));
        usernameField.shouldBe(enabled, Duration.ofSeconds(3));
        passwordField.shouldBe(visible, Duration.ofSeconds(3));
        passwordField.shouldBe(enabled, Duration.ofSeconds(3));
    }

    public SelenideElement getModal(){
        return modalWindow;
    }

    public void closeModal(){
        closeButton.shouldBe(enabled).click();
    }

}
