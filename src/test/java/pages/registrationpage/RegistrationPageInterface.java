package pages.registrationpage;

import helpers.AlertTypes;
import valueObjects.User;

public interface RegistrationPageInterface {

    public RegistrationPageInterface registration(User user, AlertTypes alert);
}
