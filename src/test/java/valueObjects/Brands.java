package valueObjects;

import java.util.List;

public class Brands {
    private static final List<String> ALLOWED_MONITORS = List.of("apple", "asus");
    private static final List<String> ALLOWED_LAPTOPS = List.of("sony vaio", "macbook", "dell");
    private static final List<String> ALLOWED_PHONES = List.of("samsung", "nokia", "nexus", "iphone", "sony", "htc");

    public static List<String> getAllowedLaptops() {
        return ALLOWED_LAPTOPS;
    }

    public static List<String> getAllowedPhones() {
        return ALLOWED_PHONES;
    }

    public static List<String> getAllowedMonitors() {
        return ALLOWED_MONITORS;
    }
}
