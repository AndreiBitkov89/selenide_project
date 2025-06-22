package pages.loginPage;

import enums.AlertType;
import valueObjects.User;

public interface LoginPage {

    LoginPage login(User user);

    void loginExpectingError(User user, AlertType expectedAlert);

    LoginPage get();

}
