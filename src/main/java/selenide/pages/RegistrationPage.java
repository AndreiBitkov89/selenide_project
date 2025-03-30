package selenide.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import selenide.LoadableComponent;
import selenide.utilsAndHelpers.NavBar;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationPage extends LoadableComponent {
    private final SelenideElement usernameField = $("#sign-username");
    private final SelenideElement passwordField = $("#sign-password");
    private final SelenideElement confirmButton = $("#signInModal  button.btn.btn-primary");
    private final SelenideElement modalWindow = $("#signInModal .modal-body");
    private final SelenideElement signupLabel = $("#signInModalLabel");
    private final SelenideElement closeButton = $(By.xpath("//*[@id='signInModal']//ancestor::button[text()='Close']"));
    MainPage mp = new MainPage();


    @Step("Регистрация пользователя {login}")
    public void registration(String login, String pass, String dialogText) {
        mp.waitUntilLoaded();

        Allure.step("Переходим на страницу регистрации", () -> {
            mp.gotoNavBar(NavBar.SIGN);
        });

        waitUntilLoaded();
        sleep(300);

        Allure.step("Заполняем логин и пароль", () -> {
            usernameField.shouldBe(enabled).setValue(login);
            passwordField.shouldBe(enabled).setValue(pass);
        });

        Allure.step("Подтверждаем регистрацию", () -> {
            confirmButton.click();
        });

        Allure.step("Получаем алерт об успешной регистрации", () -> {
            Selenide.confirm(dialogText);
        });


    }

    public void waitUntilLoaded() {
        signupLabel.shouldBe(visible, Duration.ofSeconds(3));

    }

    public SelenideElement getModal() {
        return modalWindow;
    }

    public void closeModal() {
        closeButton.shouldBe(enabled).click();
    }

}
