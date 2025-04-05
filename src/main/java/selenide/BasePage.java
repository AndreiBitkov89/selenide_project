package selenide;

public abstract class BasePage<T extends BasePage<T>> extends org.openqa.selenium.support.ui.LoadableComponent<T> {
    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        load();
        isLoaded();
        return (T) this;
    }
}
