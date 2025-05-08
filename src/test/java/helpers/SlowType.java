package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;

public class SlowType {
    public void slowType(SelenideElement element, String text) {
        element.shouldBe(visible);
        element.click();
        Selenide.sleep(100);
        for (char ch : text.toCharArray()) {
            element.sendKeys(Character.toString(ch));
            Selenide.sleep(100);
        }
    }


}

