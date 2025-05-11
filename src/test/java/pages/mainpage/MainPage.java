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
    private ElementsCollection productCards = $$(".card");

    @Override
    public void load() {
        open(Configuration.baseUrl);
    }

    @Override
    public void isLoaded() {
        getPageTitle().shouldBe(visible);
    }

    public static void gotoItemPage(String title) {
        Allure.step("Go to item page", () -> {
            SelenideElement titleItem = $(By.xpath("//a[text()='" + title + "']"));
            titleItem.shouldBe(visible).click();
        });
    }

    public static SelenideElement getPageTitle() {
        return PAGE_TITLE;
    }

    public List<ProductCardElement> getAllProducts() {
        productCards.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(5));

        return productCards.stream()
                .map(ProductCardElement::new)
                .toList();
    }

    public ProductCardElement getProductByIndex(int index) {
        return new ProductCardElement(productCards.get(index));
    }

    public ProductCardElement getProductByTitle(String title) {
        List<ProductCardElement> products = getAllProducts();
        return products.stream()
                .filter(product -> product.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + title));
    }

    public ProductCardElement getProductByPrice(int price) {
        return getAllProducts().stream()
                .filter(product -> product.getPrice() == price)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found with price: " + price));
    }

    public ProductCardElement getProductByDescription(String text) {
        return getAllProducts().stream()
                .filter(product -> product.getDescription().contains(text))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public ProductCardElement getCheapestProduct() {
        return getAllProducts().stream()
                .min(Comparator.comparingInt(ProductCardElement::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No products found"));
    }

    public ProductCardElement getMostExpensiveProduct() {
        return getAllProducts().stream()
                .max(Comparator.comparingInt(ProductCardElement::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No products found"));
    }

}

