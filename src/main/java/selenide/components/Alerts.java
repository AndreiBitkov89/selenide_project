package selenide.components;

public enum Alerts {
    USER_NOT_EXIST("User does not exist."),
    EMPTY_FIELDS("Please fill out Username and Password."),
    SUCCESSFUL_SIGN("Sign up successful."),
    USER_ALREADY_EXIST("This user already exist.");

    private final String message;

    Alerts(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
