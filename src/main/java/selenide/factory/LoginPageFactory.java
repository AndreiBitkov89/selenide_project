package selenide.factory;

import selenide.factory.service.FlagService;
import selenide.interfaces.LoginPage;
import selenide.webpages.LoginPageOptionA;
import selenide.webpages.LoginPageOptionB;

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
