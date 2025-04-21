package selenide.interfaces;

import selenide.helpers.Alerts;
import selenide.valueObject.User;

public interface RegistrationPage {

    public RegistrationPage registration(User user, Alerts alert);
}
