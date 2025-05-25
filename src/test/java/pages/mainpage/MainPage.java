package pages.mainpage;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import pages.BasePage;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage<MainPage> {

    private static final SelenideElement PAGE_TITLE = $("a.navbar-brand");
    private final ElementsCollection PRODUCTS = $$(".card");

    @Override
    public void load() {
        Allure.step("Open main page", () -> open(Configuration.baseUrl));
    }

    @Override
    public void isLoaded() {
        Allure.step("Check main page is loaded", () -> PAGE_TITLE.shouldBe(visible));
    }

    public static void gotoItemPage(String title) {
        Allure.step("Go to item page: " + title, () -> {
            $(By.xpath("//a[text()='" + title + "']"))
                    .shouldBe(visible)
                    .click();
        });
    }

    public static SelenideElement getPageTitle() {
        return PAGE_TITLE;
    }

    public List<ProductCardElement> getAllProducts() {
        Allure.step("Get all product cards", () ->
                PRODUCTS.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(5))
        );
        return PRODUCTS.stream().map(ProductCardElement::new).toList();
    }

    public ProductCardElement getProductByIndex(int index) {
        return Allure.step("Get product by index: " + index, () ->
                new ProductCardElement(PRODUCTS.get(index))
        );
    }

    public List<ProductCardElement> getProductsByTitle(String title) {
        return Allure.step("Get products by title: " + title, () ->
                getAllProducts().stream()
                        .filter(product -> product.getTitle().equalsIgnoreCase(title))
                        .toList()
        );
    }

    public ProductCardElement getProductByTitle(String title, int index) {
        return Allure.step("Get product by title: " + title + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByTitle(title);
            if (matches.isEmpty()) {
                throw new NoSuchElementException("No product with title: " + title);
            }
            return matches.get(index);
        });
    }

    public List<ProductCardElement> getProductsByPrice(int price) {
        return Allure.step("Get products by price: " + price, () ->
                getAllProducts().stream()
                        .filter(product -> product.getPrice() == price)
                        .toList()
        );
    }

    public ProductCardElement getProductByPrice(int price, int index) {
        return Allure.step("Get product by price: " + price + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByPrice(price);
            if (matches.isEmpty()) {
                throw new NoSuchElementException("No product with price: " + price);
            }
            return matches.get(index);
        });
    }

    public ProductCardElement getCheapestProduct() {
        return Allure.step("Get cheapest product", () ->
                getAllProducts().stream()
                        .min(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("No products found"))
        );
    }

    public ProductCardElement getMostExpensiveProduct() {
        return Allure.step("Get most expensive product", () ->
                getAllProducts().stream()
                        .max(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() -> new NoSuchElementException("No products found"))
        );
    }
}

