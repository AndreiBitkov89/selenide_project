package pages.loginPage.factory;

import pages.loginPage.LoginPage;
import pages.loginPage.LoginPageOptionA;
import pages.loginPage.LoginPageOptionB;
import pages.loginPage.factory.service.FlagService;


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
