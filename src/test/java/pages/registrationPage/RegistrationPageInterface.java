package pages.registrationPage;

import com.codeborne.selenide.Selenide;
import enums.AlertType;
import valueObjects.User;

public interface RegistrationPageInterface {

    public interface RegistrationPage {

        void fillUsername(String username);

        void fillPassword(String password);

        void clickRegister();

        default void register(User user, AlertType alert) {
            fillUsername(user.getUsername());
            fillPassword(user.getPassword());
            clickRegister();
            Selenide.confirm();
        }
    }
}
