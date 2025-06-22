package pages.registrationPage;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import pages.BasePage;
import enums.AlertType;
import wrappers.CustomAlert;
import wrappers.SlowType;
import valueObjects.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage extends BasePage<RegistrationPage> implements RegistrationPageInterface {

    private final SelenideElement usernameField = $("input#sign-username");
    private final SelenideElement passwordField = $("input#sign-password");
    private final SelenideElement confirm = $(By.xpath("//*[@onclick='register()']"));
    private final SelenideElement modal = $("#signInModal .modal-content");
    private final SelenideElement signupLabel = $("#signInModalLabel");

    @Override
    public void isLoaded() {
        signupLabel.shouldBe(visible, Duration.ofSeconds(5));
        usernameField.shouldBe(visible, Duration.ofSeconds(5));
        passwordField.shouldBe(visible, Duration.ofSeconds(5));
        confirm.shouldBe(visible, Duration.ofSeconds(5));
    }


    public void registration(User user, AlertType expectedAlert) {
        Allure.step("Fill login and password", () -> {
            SlowType.type(usernameField, user.getUsername());
            SlowType.type(passwordField, user.getPassword());
        });

        Allure.step("Accept registration", () -> confirm.click());

        Allure.step("Get alert after registration", () -> {
            CustomAlert alert = new CustomAlert(expectedAlert);
            String actualText = alert.getText();
            alert.accept();
            assertEquals(expectedAlert.getMessage(), actualText, "Alert text is incorrect");
        });
    }

    public boolean isModalDisplayed(boolean displayed) {
        if (displayed) {
            return modal.shouldBe(visible).isDisplayed();
        } else {
            return modal.shouldBe(hidden).isDisplayed();
        }

    }


}