package pages.mainpage;

public enum Categories {
    MONITORS("Monitors"),
    LAPTOPS("Laptops"),
    PHONES("Phones");

    private String message;

    Categories(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
