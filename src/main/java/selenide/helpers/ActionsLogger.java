package selenide.helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ActionsLogger {

    private final SelenideElement element;

    public ActionsLogger(SelenideElement element){
        this.element = element;
    }

    public void click() {
        System.out.println("[LOG] Click on element: " + element);
        element.click();
    }

    public void shouldBeVisible(){
        System.out.println("[LOG] Checking visibility for: " + element);
        element.shouldBe(Condition.visible);
    }

}
