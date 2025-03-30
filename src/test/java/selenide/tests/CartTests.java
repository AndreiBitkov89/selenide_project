package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.utilsAndHelpers.NavBar;

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
        mainPage.gotoNavBar(NavBar.CART);

        String itemNameInCart = cartPage.getItemName();
        assertEquals(item, itemNameInCart);
    }
}
