package pages.mainPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import static com.codeborne.selenide.Condition.visible;

public class ProductCardElement {

    private final SelenideElement rootElement;
    private final String titleSelector = ".card-title a";
    private final String priceSelector = "h5";
    private final String descriptionSelector = "p.card-text";

    public ProductCardElement(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getTitle() {
        return Allure.step("Get product title",
                () -> rootElement.$(titleSelector).shouldBe(visible).getText().trim()
        );
    }

    public int getPrice() {
        return Allure.step("Get product price", () -> {
            String priceText = rootElement.$(priceSelector)
                    .shouldBe(visible)
                    .getText()
                    .replace("$", "");
            return Integer.parseInt(priceText);
        });
    }

    public void openItem() {
        Allure.step("Open item page", () -> {
            rootElement.$(".card-title a").shouldBe(visible).click();
        });
    }

}