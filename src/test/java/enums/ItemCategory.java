package enums;

public enum ItemCategory {
    MONITORS("Monitors"),
    LAPTOPS("Laptops"),
    PHONES("Phones");

    private final String message;

    ItemCategory(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
