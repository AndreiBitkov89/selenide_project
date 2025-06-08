package steps;

import constants.AlertTypes;
import pages.PageManager;
import pages.commonComponents.NavBarComponent;
import pages.loginpage.LoginPage;
import valueObjects.User;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {

    private final NavBarComponent navBarComponent = new NavBarComponent();

    public void login(User user) {
        PageManager.loginPage().get().login(user).getModal().shouldBe(hidden);
    }

    public void loginCustom(User user, String variant) {
        PageManager.customLoginPage(variant).get().login(user).getModal().shouldBe(hidden);
    }

    public void loginWithFlag(LoginPage factory, User user) {
        factory.get().login(user).getModal().shouldBe(hidden);
    }

    public void loginWithError(User user, AlertTypes alert){
        PageManager.loginPage().get().wrongLogin(user, alert);
    }

    public void shouldSeeUsername() {
        navBarComponent.usernameAfterLogin().shouldBe(visible);
    }


    public void shouldNotSeeUsername() {
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);
    }

    public void logout() {
        navBarComponent.goTo(navBarComponent.logout())
                .usernameAfterLogin().shouldBe(hidden);
    }
}