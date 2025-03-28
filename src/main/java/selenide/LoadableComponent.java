package selenide;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

public abstract class LoadableComponent {

    protected abstract void waitUntilLoaded();
}
