package pages.loginPage;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import wrappers.CustomAlert;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import pages.BasePage;
import enums.AlertType;
import valueObjects.User;
import wrappers.SlowType;

public class LoginPageOptionA extends BasePage<LoginPageOptionA> implements LoginPage {

    private final SelenideElement usernameField = $("#loginusername");
    private final SelenideElement passwordField = $("#loginpassword");
    private final SelenideElement confirmButton = $(By.xpath("//*[@onclick='logIn()']"));
    private final SelenideElement loginLabel = $("#logInModalLabel");
    private final SelenideElement modal = $("#logInModal .modal-body");
    private final SelenideElement closeModal = $("div#logInModal .modal-footer button:nth-of-type(1)");

    @Override
    protected void isLoaded() {
        Allure.step("Check that login modal is visible", () -> {
            loginLabel.shouldBe(visible);
            usernameField.shouldBe(visible);
            passwordField.shouldBe(visible);
            closeModal.shouldBe(visible);
        });
    }

    @Override
    public LoginPageOptionA login(User user) {
        Allure.step("Fill in username and password slowly", () -> {
            SlowType.type(usernameField, user.getUsername());
            SlowType.type(passwordField, user.getPassword());
        });

        Allure.step("Click login button", () -> {
            confirmButton.click();
        });

        return this;
    }

    public void isModalVisible() {
        modal.shouldBe(visible);
    }

    public void isModalHidden() {
        modal.shouldBe(hidden);
    }

    @Override
    public void loginExpectingError(User user, AlertType expectedAlert) {
        login(user);
        Allure.step("Expect alert after failed login", () -> {
            new CustomAlert(expectedAlert).accept();
        });
    }

    @Override
    public LoginPageOptionA get() {
        return super.get();
    }
}
