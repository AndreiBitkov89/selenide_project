package pages.mainPage;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import pages.BasePage;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage<MainPage> {

    private static final SelenideElement pageTitle = $("a.navbar-brand");
    private static final ElementsCollection products = $$(".card");

    private static final String PRODUCT_TITLE_XPATH = "//a[text()='%s']";


    @Override
    public void isLoaded() {
        Allure.step("Check main page is loaded", () -> pageTitle.shouldBe(visible));
    }

    public void gotoItemPage(String title) {
        Allure.step("Go to item page: " + title, () -> {
            $x(String.format(PRODUCT_TITLE_XPATH, title)).shouldBe(visible).click();
        });
    }

    public List<ProductCardElement> getAllProducts() {
        Allure.step("Get all product cards", () ->
                products.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(5))
        );
        return products.stream().map(ProductCardElement::new).toList();
    }

    public ProductCardElement getProductByIndex(int index) {
        return Allure.step("Get product by index: " + index, () ->
                new ProductCardElement(products.get(index))
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
        return Allure.step("Get products by title: " + title + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByTitle(title);
            if (matches.isEmpty()) {
                Assertions.fail("No product with title: " + title);
            }
            return Allure.step("Return product with index", () -> matches.get(index));
        });
    }

    public List<ProductCardElement> getProductsByPrice(int price) {
        return Allure.step("Get products by price: " + price, () ->
                getAllProducts().stream()
                        .filter(product -> product.getPrice() == price)
                        .toList()
        );
    }

    //todo с индексом не понимаю как может использоваться
    //оставил - может быть использовано если получаем несколько товаров с одинаковой ценой - могу взять первый или не первый
    public ProductCardElement getProductByPrice(int price, int index) {
        return Allure.step("Get product by price: " + price + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByPrice(price);
            if (matches.isEmpty()) {
                Assertions.fail("No product with price: " + price);
            }
            return matches.get(index);
        });
    }

    public ProductCardElement getCheapestProduct() {
        return Allure.step("Get cheapest product", () ->
                getAllProducts().stream()
                        .min(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() ->
                                Assertions.fail("No product found"))
        );
    }

    public ProductCardElement getMostExpensiveProduct() {
        return Allure.step("Get most expensive product", () ->
                getAllProducts().stream()
                        .max(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() ->
                                Assertions.fail("No product found"))
        );
    }

    public MainPage openMain(){
        pageTitle.click();
        return this;
    }
}

