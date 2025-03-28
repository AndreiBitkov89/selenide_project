package selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.util.List;

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


    public void gotoLogin() {
        this.loginButton.click();
    }

    public void gotoRegistration() {
        this.signInButton.click();
    }


    @Override
    public void waitUntilLoaded() {
        this.title.shouldBe(visible);
    }


    @Step("Проверка отображения имени авторизованного юзера")
    public void shouldShowWelcome(String name) {
        username.shouldBe(visible).shouldHave(text(name));
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

    @Step("Сохраняем число элементов и переходитм к фильтрации")
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

}

