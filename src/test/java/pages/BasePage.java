package pages;

public abstract class BasePage<T extends BasePage<T>> {

    @SuppressWarnings("unchecked")
    public T get() {
        isLoaded();
        return (T) this;
    }

    protected abstract void isLoaded();
}