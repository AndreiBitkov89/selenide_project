package pages.loginpage;

import com.codeborne.selenide.SelenideElement;
import constants.AlertTypes;
import decorators.CustomAlert;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.commonComponents.NavBarComponent;
import decorators.SlowType;
import valueObjects.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageOptionB extends BasePage<LoginPageOptionB> implements LoginPage {

    private final SelenideElement USERNAME_FIELD = $("#loginusername");
    private final SelenideElement PASSWORD_FIELD = $("#loginpassword");
    private final SelenideElement CONFIRM_BUTTON = $(By.xpath("//*[@onclick='logIn()']"));
    private final SelenideElement LOGIN_LABEL = $("#logInModalLabel");
    private final SelenideElement MODAL = $("#logInModal .modal-body");
    private final SelenideElement CLOSE_MODAL = $("div#logInModal .modal-footer button:nth-of-type(1)");
    private final NavBarComponent NAVBAR_COMPONENT = new NavBarComponent();
    private final SlowType TYPE = new SlowType();

    @Override
    protected void load() {
        Allure.step("Open main page and navigate to login modal", () ->
                NAVBAR_COMPONENT.goTo(NAVBAR_COMPONENT.getLogin())
        );
    }

    @Override
    protected void isLoaded() {
        Allure.step("Check that login modal is visible", () -> {
            LOGIN_LABEL.shouldBe(visible);
            USERNAME_FIELD.shouldBe(visible);
            PASSWORD_FIELD.shouldBe(visible);
            CLOSE_MODAL.shouldBe(visible);
        });
    }

    @Override
    public LoginPageOptionB login(User user) {
        Allure.step("Fill login and password", () -> {
            TYPE.slowType(getUsernameField(), user.getUsername());
            TYPE.slowType(getPasswordField(), user.getPassword());
        });

        Allure.step("Submit login form", () -> getConfirmButton().click());
        return this;
    }

    @Override
    public void wrongLogin(User user, AlertTypes expectedAlert) {
        login(user);
        Allure.step("Check alert after failed login", () -> {
            CustomAlert alert = new CustomAlert(expectedAlert);
            alert.accept();
        });
    }

    @Override
    public SelenideElement getModal() {
        return MODAL;
    }

    @Override
    public SelenideElement getUsernameField() {
        return USERNAME_FIELD;
    }

    @Override
    public SelenideElement getPasswordField() {
        return PASSWORD_FIELD;
    }

    @Override
    public SelenideElement getConfirmButton() {
        return CONFIRM_BUTTON;
    }

    @Override
    public LoginPageOptionB get() {
        return super.get();
    }
}