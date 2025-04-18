package selenide.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage extends BasePage<MainPage> {

    @FindBy(css="a.navbar-brand")
    private WebElement title;

    @FindBy(css="div#fotcont")
    private WebElement footer;

    @Override
    public void load() {
        open(Configuration.baseUrl);
        PageFactory.initElements(WebDriverRunner.getWebDriver(), this);
    }

    @Override
    protected void isLoaded() {
        $(title).shouldBe(visible);
    }

    public static void gotoItemPage(String title) {
        SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
        titleItem.shouldBe(visible).click();
    }

}

