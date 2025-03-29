package selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends LoadableComponent {

    private final SelenideElement signInButton = $("#signin2");
    private final SelenideElement username = $("#nameofuser");
    private final SelenideElement loginButton = $("#login2");
    private final SelenideElement title = $("a.navbar-brand");
    private final SelenideElement categoryBlock = $(".list-group");
    private final SelenideElement footer = $("div#fotcont");
    private final SelenideElement phonesCategory = categoryBlock.$(byText("Phones"));
    private final SelenideElement laptopsCategory = categoryBlock.$(byText("Laptops"));
    private final SelenideElement monitorsCategory = categoryBlock.$(byText("Monitors"));
    private final ElementsCollection item = $$(".card-title a");
    ItemPage itemPage = new ItemPage();


    public void gotoLogin() {

        Allure.step("Переход к модальному окну логина", () -> {
            this.loginButton.click();
        });
    }

    public void gotoRegistration() {
        Allure.step("Переход к модальному окну регистрации", () -> {
            this.signInButton.click();
        });
    }


    @Override
    public void waitUntilLoaded() {
        this.title.shouldBe(visible);
    }


    public void shouldShowWelcome(String name) {
        Allure.step("Поверяем наличие имени юзера на главной странице после логина", () -> {
            username.shouldBe(visible).shouldHave(text(name));
        });

    }

    public SelenideElement getFooter() {
        return footer;
    }

    public SelenideElement getUsernameAfterLogin() {
        return username;
    }

    public void filterPhones() {
        Allure.step("Фильтруем телефоны", () -> {
            phonesCategory.shouldBe(enabled).click();
        });

    }


    public void filterLaptops() {
        Allure.step("Фильтруем ноутбуки", () -> {
            laptopsCategory.shouldBe(enabled).click();
        });

    }

    public void filterMonitors() {
        Allure.step("Фильтруем мониторы", () -> {
            monitorsCategory.shouldBe(enabled).click();
        });

    }

    public ElementsCollection getItems() {
        return item;
    }

    @Step("Сохраняем число элементов и переходим к фильтрации")
    public List<String> filterItems(String filter) {
        this.waitUntilLoaded();
        List<String> initialItems = this.getItems().shouldHave(sizeGreaterThan(0)).texts();
        if (filter == "phone") {
            this.filterPhones();
        } else if (filter == "laptop") {
            this.filterLaptops();
        } else if (filter == "monitor") {
            this.filterMonitors();
        }


        List<String> filteredItems = this.getItems().shouldHave(sizeLessThan(initialItems.size())).texts();

        return filteredItems;

    }

    public void gotoItem(String title) {
        waitUntilLoaded();
        SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
        titleItem.shouldBe(enabled).click();
        itemPage.waitUntilLoaded();
    }


    /*Тут будет дополнительная логика рабы с навигационным баром - поэтому if else
     */
    public void gotoNavBar(String barTitle) {
        if (Objects.equals(barTitle, "cart")) {
            Allure.step("Переходим в корзину", () -> {
                $("#navbarExample #cartur").shouldBe(enabled).click();
            });

        }

    }
}

