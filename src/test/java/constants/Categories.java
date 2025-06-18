package constants;

public enum Categories {
    MONITORS("Monitors"),
    LAPTOPS("Laptops"),
    PHONES("Phones");

    //todo верхний регистр
    private final String MESSAGE;

    Categories(String message) {
        this.MESSAGE = message;
    }

    //todo название метода
    public String getMESSAGE() {
        return MESSAGE;
    }
}
