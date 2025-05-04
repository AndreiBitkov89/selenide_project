package selenide.interfaces;

import com.codeborne.selenide.SelenideElement;
import selenide.valueObject.User;

public interface LoginPage {

    LoginPage login(User user);

    LoginPage get();

    SelenideElement getModal();

}
