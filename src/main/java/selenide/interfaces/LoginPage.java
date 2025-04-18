package selenide.interfaces;

import com.codeborne.selenide.SelenideElement;
import selenide.components.Alerts;
import selenide.valueObject.User;

public interface LoginPage {

    LoginPage login(User user);

    LoginPage wrongLogin(User user, Alerts expectedAlert);

    SelenideElement getModal();
}
