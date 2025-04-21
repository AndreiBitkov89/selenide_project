package selenide.webpages;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import selenide.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage<MainPage> {

    private static SelenideElement title = $("a.navbar-brand");
    private SelenideElement footer = $("div#fotcont");
    private SelenideElement carousel = $("div#contcar");

    @Override
    public void load() {
        open(Configuration.baseUrl);
    }

    @Override
    public void isLoaded() {
        getTitle().shouldBe(visible);
    }

    public static void gotoItemPage(String title) {
        Allure.step("Go to item page", () -> {
            SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
            titleItem.shouldBe(visible).click();
        });
    }

    public SelenideElement getCarousel() {
        return carousel;
    }

    public static SelenideElement getTitle() {
        return title;
    }
}

