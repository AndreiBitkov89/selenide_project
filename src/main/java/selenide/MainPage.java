package selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement signInButton = $("#signin2");
    public SelenideElement username = $("#nameofuser");
    private SelenideElement loginButton = $("#login2");
    private SelenideElement title =$("a.navbar-brand");

    public void gotoLogin() {
        this.loginButton.click();
    }

    public void gotoRegistration() {
        this.signInButton.click();
    }

    public void waitMainPageToBeLoaded(){
        title.shouldBe(visible);
    }

    public void shouldShowWelcome(String name) {
        username.shouldBe(visible).shouldHave(text(name));
    }


}
