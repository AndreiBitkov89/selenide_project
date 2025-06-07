package steps;

import constants.AlertTypes;
import io.qameta.allure.Step;
import pages.PageManager;
import pages.commonComponents.NavBarComponent;
import pages.loginpage.LoginPage;
import valueObjects.User;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {

    private NavBarComponent navBarComponent = new NavBarComponent();

    @Step("Login as user: {user.username}")
    public void login(User user) {
        PageManager.loginPage().get().login(user).getModal().shouldBe(hidden);
    }

    @Step("Login using redesigned variant: {variant}")
    public void loginCustom(User user, String variant) {
        PageManager.customLoginPage(variant).get().login(user).getModal().shouldBe(hidden);
    }

    @Step("Login using login page from factory with user: {user.username}")
    public void loginWithFlag(LoginPage factory, User user) {
        factory.get().login(user).getModal().shouldBe(hidden);
    }

    @Step("Login with error")
    public void loginWithError(User user, AlertTypes alert){
        PageManager.loginPage().get().wrongLogin(user, alert);
    }

    @Step("Assert username is visible after login")
    public void shouldSeeUsername() {
        navBarComponent.usernameAfterLogin().shouldBe(visible);
    }


    @Step("Assert username is not visible after failed login")
    public void shouldNotSeeUsername() {
        navBarComponent.usernameAfterLogin().shouldNotBe(visible);
    }

    @Step("Logout")
    public void logout() {
        navBarComponent.goTo(navBarComponent.logout())
                .usernameAfterLogin().shouldBe(hidden);
    }
}