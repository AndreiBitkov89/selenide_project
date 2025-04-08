package selenide.helpers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class SlowType {
    public void slowType(SelenideElement element, String text) {
        element.shouldBe(visible, enabled).click();
        for (char ch : text.toCharArray()) {
            element.sendKeys(Character.toString(ch));
        }
    }

}

