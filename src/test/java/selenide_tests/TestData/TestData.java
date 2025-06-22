package selenide_tests.TestData;

import valueObjects.*;

public class TestData {

    public static final Item GALAXY_S7 = new Item("Samsung galaxy s7", 800);
    public static final Item NOKIA_PHONE = new Item("Nokia lumia 1520", 820);
    public static final Item HTC_PHONE = new Item("HTC One M9", 700);
    public static final Item APPLE_MONITOR = new Item("Apple monitor 24", 400);

    public static final Purchase DEFAULT_PURCHASE = Purchase.builder()
            .withName("QA")
            .withCountry("Germany")
            .withCity("Berlin")
            .withCreditCard("1234567")
            .withMonth("01")
            .withYear("2026")
            .build();

}
