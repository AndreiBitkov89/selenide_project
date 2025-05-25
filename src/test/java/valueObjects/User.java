package valueObjects;

public class User {

    private final String USERNAME;
    private final String PASSWORD;

    public User(String username, String password) {
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
