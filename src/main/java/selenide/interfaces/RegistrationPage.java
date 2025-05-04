package selenide.interfaces;

import selenide.helpers.AlertTypes;
import selenide.valueObject.User;

public interface RegistrationPage {

    public RegistrationPage registration(User user, AlertTypes alert);
}
