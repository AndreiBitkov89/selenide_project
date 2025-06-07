package pages.registrationpage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import constants.AlertTypes;
import valueObjects.User;

public interface RegistrationPageInterface {

    public SelenideElement getUsernameField();

    public SelenideElement getPasswordField();

    public SelenideElement getConfirmButton();

    default RegistrationPageInterface registration(User user, AlertTypes alert){
        getUsernameField().sendKeys(user.getUsername());
        getPasswordField().sendKeys(user.getUsername());

        getConfirmButton().click();
        Selenide.confirm();

        return this;
    };
}
