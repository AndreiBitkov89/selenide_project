package helpers;

import java.security.SecureRandom;

public class CredentialsGenerator {

    private static final String USERNAME_CHARS = "0123456789";
    private static final String PASSWORD_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-+=<>?";

    private static final SecureRandom random = new SecureRandom();

    public static String generateUsername(int length) {
        return "username"+ generateRandomString(USERNAME_CHARS, length);
    }

    public static String generatePassword(int length) {
        return generateRandomString(PASSWORD_CHARS, length);
    }

    private static String generateRandomString(String charSet, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charSet.length());
            result.append(charSet.charAt(index));
        }
        return result.toString();
    }
}
