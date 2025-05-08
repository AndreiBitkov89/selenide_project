package pages.loginpage.factory;

import pages.loginpage.LoginPage;
import pages.loginpage.LoginPageOptionA;
import pages.loginpage.LoginPageOptionB;
import pages.loginpage.factory.service.FlagService;


public class LoginPageFactory {
    public static LoginPage getFlagFromServer() {
        String variant = FlagService.getLoginVariant(); // делает HTTP запрос
        return switch (variant) {
            case "A" -> new LoginPageOptionA();
            case "B" -> new LoginPageOptionB();
            default -> throw new IllegalStateException("Unknown flag: " + variant);
        };
    }
}
