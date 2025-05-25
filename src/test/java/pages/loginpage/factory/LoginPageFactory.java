package pages.loginpage.factory;

import pages.loginpage.LoginPage;
import pages.loginpage.LoginPageOptionA;
import pages.loginpage.LoginPageOptionB;
import pages.loginpage.factory.service.FlagService;


public class LoginPageFactory {
    public static LoginPage getFlagFromServer() {
        String flag = FlagService.getLoginVariant();
        return switch (flag) {
            case "A" -> new LoginPageOptionA();
            case "B" -> new LoginPageOptionB();
            default -> throw new IllegalStateException("Unknown flag: " + flag);
        };
    }
}
