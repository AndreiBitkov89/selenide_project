package pages.loginpage;

import com.codeborne.selenide.SelenideElement;
import helpers.AlertTypes;
import valueObjects.User;

public interface LoginPage {

    LoginPage wrongLogin(User user, AlertTypes expectedAlert);

    LoginPage get();

    SelenideElement getMODAL();

    SelenideElement getUSERNAME_FIELD();

    SelenideElement getPASSWORD_FIELD();

    SelenideElement getCONFIRM_BUTTON();

    default LoginPage login(User user) {
        getUSERNAME_FIELD().sendKeys(user.getUsername());
        getPASSWORD_FIELD().sendKeys(user.getPassword());
        getCONFIRM_BUTTON().click();
        return this;
    }

}
