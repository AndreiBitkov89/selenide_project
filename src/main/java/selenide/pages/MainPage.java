package selenide.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import selenide.BasePage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage<MainPage> {

    private final SelenideElement username = $("#nameofuser");
    private final SelenideElement title = $("a.navbar-brand");
    private final SelenideElement footer = $("div#fotcont");

    public void shouldShowWelcome(String name) {
        Allure.step("Поверяем наличие имени юзера на главной странице после логина", () -> {
            username.shouldBe(visible).shouldHave(text(name));
        });
    }

    public SelenideElement getUsernameAfterLogin() {
        return username;
    }

    public static void gotoItemPage(String title) {
        SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
        titleItem.shouldBe(visible).click();
    }

    public SelenideElement getFooter() {
        return footer;
    }

    @Override
    public void load() {
        open("https://www.demoblaze.com/");
    }

    @Override
    protected void isLoaded() {
        title.shouldBe(visible);
    }
}

