package pages.loginpage;

import com.codeborne.selenide.SelenideElement;
import valueObjects.User;

public interface LoginPage {

    LoginPage login(User user);

    LoginPage get();

    SelenideElement getModal();

}
