package selenide.helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class Decorator {
    public void slowType(SelenideElement element, String text) {
        element.shouldBe(visible, enabled);
        for (char ch : text.toCharArray()) {
            Selenide.sleep(20);
            element.sendKeys(Character.toString(ch));
            Selenide.sleep(20);
        }
    }



}

