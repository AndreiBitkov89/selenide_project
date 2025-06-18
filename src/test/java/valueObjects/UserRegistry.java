package valueObjects;

import utils.CredentialsGenerator;

import java.util.HashMap;
import java.util.Map;

import static config.ConfigProvider.CONFIG;

public class UserRegistry {

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("default", new User(CONFIG.username(), CONFIG.password()));
    }

    public static User get(String key) {
        return users.get(key);
    }

    public static void createRandomUser(String key) {
        String username = CredentialsGenerator.generateUsername(8);
        String password = CredentialsGenerator.generatePassword(8);
        User user = new User(username, password);
        users.put(key, user);
    }

}
