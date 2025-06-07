package pages.registrationpage;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import pages.BasePage;
import constants.AlertTypes;
import pages.commonComponents.NavBarComponent;
import decorators.SlowType;
import valueObjects.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage extends BasePage<RegistrationPage> implements RegistrationPageInterface {

    private final SelenideElement USERNAME_FIELD = $("input#sign-username");
    private final SelenideElement PASSWORD_FIELD = $("input#sign-password");
    private final SelenideElement CONFIRM = $(By.xpath("//*[@onclick='register()']"));
    private final SelenideElement MODAL = $("#signInModal .modal-content");
    private final SelenideElement SIGNUP_LABEL = $("#signInModalLabel");

    private final NavBarComponent navBarComponent = new NavBarComponent();
    private final SlowType slowType = new SlowType();

    @Override
    public void load() {
        navBarComponent.goTo(navBarComponent.getSignUp());
    }

    @Override
    public void isLoaded() {
        SIGNUP_LABEL.shouldBe(visible, Duration.ofSeconds(5));
        USERNAME_FIELD.shouldBe(visible, Duration.ofSeconds(5));
        PASSWORD_FIELD.shouldBe(visible, Duration.ofSeconds(5));
        CONFIRM.shouldBe(visible, Duration.ofSeconds(5));
    }

    public SelenideElement getUsernameField() {
        return USERNAME_FIELD;
    }

    public SelenideElement getPasswordField() {
        return PASSWORD_FIELD;
    }

    public SelenideElement getConfirmButton() {
        return CONFIRM;
    }

    public RegistrationPage registration(User user, AlertTypes expectedAlert) {
        Allure.step("Fill login and password", () -> {
            slowType.slowType(getUsernameField(), user.getUsername());
            slowType.slowType(getPasswordField(), user.getPassword());
        });

        Allure.step("Accept registration", () -> getConfirmButton().click());

        Allure.step("Get alert after registration", () -> {
            String dialog = Selenide.confirm();
            assertEquals(expectedAlert.getMessage(), dialog);
        });

        return this;
    }

    public SelenideElement getModal() {
        return MODAL;
    }
}