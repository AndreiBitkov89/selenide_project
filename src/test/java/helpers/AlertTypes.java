package helpers;

public enum AlertTypes {
    USER_NOT_EXIST("User does not exist."),
    EMPTY_FIELDS("Please fill out Username and Password."),
    SUCCESSFUL_SIGN("Sign up successful."),
    USER_ALREADY_EXIST("This user already exist.");

    public final String message;

    AlertTypes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
