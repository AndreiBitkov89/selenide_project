package selenide_tests;

import org.junit.jupiter.api.*;
import pages.PageManager;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static config.ConfigProvider.*;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeAll
    public static void initialize() {
        browser = CONFIG.browser();
        browserSize = "1900x1400";
        fastSetValue = false;
        headless = true;
        baseUrl = CONFIG.baseUrl();
        timeout = 10000;

    }

    @BeforeEach
    public void startTest() {
        open(baseUrl);
    }

    @AfterEach
    public void clearData() {
        clearBrowserCookies();
    }

    @AfterAll
    public static void cleanPages() {
        PageManager.reset();
    }
}
