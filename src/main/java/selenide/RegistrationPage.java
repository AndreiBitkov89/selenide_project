package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends LoadableComponent{
    private final SelenideElement usernameField = $("#sign-username");
    private final SelenideElement passwordField = $("#sign-password");
    private final SelenideElement confirmButton = $("#signInModal  button.btn.btn-primary");
    private final SelenideElement modalWindow = $("#signInModal .modal-body");
    private final SelenideElement signupLabel = $("#signInModalLabel");
    private final SelenideElement closeButton = $(By.xpath("//*[@id='signInModal']//ancestor::button[text()='Close']"));
    MainPage mp = new MainPage();


    public void registration(String login, String pass, String dialogText) {
        mp.waitUntilLoaded();
        mp.gotoRegistration();
        waitUntilLoaded();
        usernameField.shouldBe(visible).shouldBe(enabled).setValue(login);
        passwordField.shouldBe(visible).shouldBe(enabled).setValue(pass);
        confirmButton.click();

        Selenide.confirm(dialogText);

    }

    public void waitUntilLoaded() {
        signupLabel.shouldBe(visible, Duration.ofSeconds(3));

    }

    public SelenideElement getModal(){
        return modalWindow;
    }

    public void closeModal(){
        closeButton.shouldBe(enabled).click();
    }

}
