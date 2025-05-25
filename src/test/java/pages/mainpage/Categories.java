package pages.mainpage;

public enum Categories {
    MONITORS("Monitors"),
    LAPTOPS("Laptops"),
    PHONES("Phones");

    private final String MESSAGE;

    Categories(String message) {
        this.MESSAGE = message;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}
