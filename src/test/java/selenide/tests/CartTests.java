package selenide.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.CartPage;
import selenide.ItemPage;
import selenide.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    @Test
    @Severity(CRITICAL)
    void shouldAddItemToCart() {
        String item = "Samsung galaxy s7";

        mainPage.gotoItem(item);
        assertEquals(item, itemPage.getItemName());

        itemPage.addItemToCart();
        mainPage.gotoNavBar("cart");

        String itemNameInCart = cartPage.getItemName();
        assertEquals(item, itemNameInCart);
    }
}
