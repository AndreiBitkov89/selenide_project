package selenide.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import selenide.PageFactory;
import static config.ConfigProvider.CONFIG;


import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeEach
    public void initialize() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1900x1400";
        Configuration.fastSetValue = false;
        Configuration.headless = false;
        open(CONFIG.baseUrl());

    }

    @AfterEach
    public void clearState() {
        clearBrowserCookies();
        PageFactory.reset();
    }
}
