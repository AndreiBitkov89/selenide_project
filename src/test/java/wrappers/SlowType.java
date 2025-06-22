package wrappers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public final class SlowType {

    public static void type(SelenideElement element, String text) {
        element.shouldBe(visible);
        element.click();
        Selenide.sleep(80);

        for (char ch : text.toCharArray()) {
            element.sendKeys(Character.toString(ch));
            Selenide.sleep(80);
        }
    }
}

