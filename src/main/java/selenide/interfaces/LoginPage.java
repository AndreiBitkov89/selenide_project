package selenide.interfaces;

import com.codeborne.selenide.SelenideElement;
import selenide.helpers.Alerts;
import selenide.valueObject.User;

public interface LoginPage {

    LoginPage login(User user);

    LoginPage wrongLogin(User user, Alerts expectedAlert);

    SelenideElement getModal();
}
