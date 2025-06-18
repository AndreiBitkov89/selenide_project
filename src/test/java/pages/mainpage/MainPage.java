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

    //todo верхний регистр
    private static final SelenideElement PAGE_TITLE = $("a.navbar-brand");
    private static final ElementsCollection PRODUCTS = $$(".card");

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
            //todo локатор можно в поля
            $(By.xpath("//a[text()='" + title + "']"))
                    //todo избыточно
                    .shouldBe(visible)
                    .click();
        });
    }

    //todo возвращаем SelenideElement? :((
    public static SelenideElement getPageTitle() {
        return Allure.step("Return title of main page", () -> PAGE_TITLE);
    }

    //todo static ?
    public static List<ProductCardElement> getAllProducts() {
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
        return Allure.step("Get products by title: " + title + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByTitle(title);
            if (matches.isEmpty()) {
                //todo может быть падать с ошибкой Assertions.fail() ?
                throw new NoSuchElementException("No product with title: " + title);
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
    public ProductCardElement getProductByPrice(int price, int index) {
        return Allure.step("Get product by price: " + price + ", index: " + index, () -> {
            List<ProductCardElement> matches = getProductsByPrice(price);
            if (matches.isEmpty()) {
                //todo может быть падать с ошибкой Assertions.fail() ?
                throw new NoSuchElementException("No product with price: " + price);
            }
            return matches.get(index);
        });
    }

    public ProductCardElement getCheapestProduct() {
        return Allure.step("Get cheapest product", () ->
                getAllProducts().stream()
                        .min(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() ->
                                //todo может быть падать с ошибкой Assertions.fail() ?
                                new NoSuchElementException("No products found"))
        );
    }

    public ProductCardElement getMostExpensiveProduct() {
        return Allure.step("Get most expensive product", () ->
                getAllProducts().stream()
                        .max(Comparator.comparingInt(ProductCardElement::getPrice))
                        .orElseThrow(() ->
                                //todo может быть падать с ошибкой Assertions.fail() ?
                                new NoSuchElementException("No products found"))
        );
    }
}

