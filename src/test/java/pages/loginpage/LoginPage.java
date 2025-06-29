package pages.loginpage;

import com.codeborne.selenide.SelenideElement;
import constants.AlertTypes;
import valueObjects.User;

public interface LoginPage {

    void wrongLogin(User user, AlertTypes expectedAlert);

    LoginPage get();

    SelenideElement getModal();

    SelenideElement getUsernameField();

    SelenideElement getPasswordField();

    SelenideElement getConfirmButton();

    default LoginPage login(User user) {
        getUsernameField().sendKeys(user.getUsername());
        getPasswordField().sendKeys(user.getPassword());
        getConfirmButton().click();
        return this;
    }

}
