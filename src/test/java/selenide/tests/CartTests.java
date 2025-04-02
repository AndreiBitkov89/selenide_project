package selenide.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import selenide.pages.CartPage;
import selenide.pages.ItemPage;
import selenide.pages.MainPage;
import selenide.components.NavBar;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTests extends BaseTest {

    private MainPage mainPage = new MainPage();
    private CartPage cartPage = new CartPage();
    private ItemPage itemPage = new ItemPage();
    private int itemPrice;
    private final String PHONE_ITEM = "Samsung galaxy s7";

    @Test
    @Severity(CRITICAL)
    public void shouldAddItemToCart() {

        mainPage.gotoItemPage(PHONE_ITEM);
        assertEquals(PHONE_ITEM, itemPage.getItemName());

        itemPage.addItemToCart();
        itemPrice = itemPage.returnPrice();
        NavBar.CART.gotoNavBar();

        cartPage.getItemInCart(PHONE_ITEM).shouldBe(visible);
        assertEquals(itemPrice, cartPage.getPriceOfItemInCart(PHONE_ITEM));
    }
}
