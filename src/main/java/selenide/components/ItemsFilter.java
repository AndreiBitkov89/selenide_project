package selenide.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public enum ItemsFilter {
    PHONE("//*[@class ='list-group']//*[text()='Phones']"),
    LAPTOP("//*[@class ='list-group']//*[text()='Laptops']"),
    MONITOR("//*[@class ='list-group']//*[text()='Monitors']");

    private final String selector;

    ItemsFilter(String selector) {
        this.selector = selector;
    }

    public SelenideElement getElement() {
        return $x(selector);
    }


    public void applyFilter() {
        Allure.step(this.logging(), () -> {
            this.getElement().shouldBe(visible).click();
        });
    }

    public String logging() {
        return switch (this) {
            case PHONE -> "Фильтруем телефоны";
            case LAPTOP -> "Фильтруем ноутбуки";
            case MONITOR -> "Фильтруем мониторы";
        };
    }
}
