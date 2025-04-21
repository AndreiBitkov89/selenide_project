package selenide.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import selenide.PageManager;

import static com.codeborne.selenide.Configuration.*;
import static config.ConfigProvider.*;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeEach
    public void initialize() {
        browser = "chrome";
        browserSize = "1900x1400";
        fastSetValue = false;
        headless = false;
        baseUrl = CONFIG.baseUrl();
        open(baseUrl);

    }

    @AfterEach
    public void clearState() {
        clearBrowserCookies();
        PageManager.reset();
    }
}
