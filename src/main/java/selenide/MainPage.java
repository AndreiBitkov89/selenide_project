package selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final SelenideElement signInButton = $("#signin2");
    public final SelenideElement username = $("#nameofuser");
    private final SelenideElement loginButton = $("#login2");
    private final SelenideElement title = $("a.navbar-brand");
    private final SelenideElement categoryBlock = $(".list-group");
    private final SelenideElement footer = $("div#fotcont");
    private final SelenideElement phonesCategory = categoryBlock.$(".list-group [onclick=\"byCat('phone')\"]");
    private final ElementsCollection item = $$(".card-title a");

    public void gotoLogin() {
        this.loginButton.click();
    }

    public void gotoRegistration() {
        this.signInButton.click();
    }

    public void waitMainPageToBeLoaded() {
        title.shouldBe(visible, Duration.ofSeconds(3000));
    }

    public void shouldShowWelcome(String name) {
        username.shouldBe(visible).shouldHave(text(name));
    }

    public SelenideElement getFooter() {
        return footer;
    }

    public void filterPhones(){
        phonesCategory.shouldBe(enabled).click();
    }

    public  ElementsCollection getItems() {
        return item;
    }
}
