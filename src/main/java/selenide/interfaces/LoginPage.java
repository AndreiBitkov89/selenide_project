package selenide.interfaces;

import com.codeborne.selenide.SelenideElement;
import selenide.components.Alerts;
import selenide.helpers.User;

public interface LoginPage {


     public void login(User user);
     public void wrongLogin(User user, Alerts expectedAlert);
     public SelenideElement getModal();
}
