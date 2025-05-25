package pages.mainpage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class ProductCardElement {

    private final SelenideElement ROOT_ELEMENT;
    private static final String TITLE_SELECTOR = ".card-title a";
    private static final String PRICE_SELECTOR = "h5";
    private static final String DESCRIPTION_SELECTOR = "p.card-text";
    private static final String CLICKABLE_SELECTOR = "h4.card-title a";

    public ProductCardElement(SelenideElement rootElement) {
        this.ROOT_ELEMENT = rootElement;
    }

    public String getTitle() {
        return ROOT_ELEMENT.$(TITLE_SELECTOR).shouldBe(visible).getText().trim();
    }

    public int getPrice() {
        String priceText = ROOT_ELEMENT.$(PRICE_SELECTOR).shouldBe(visible).getText().replace("$", "");
        return Integer.parseInt(priceText);
    }

    public String getDescription() {
        return ROOT_ELEMENT.$(DESCRIPTION_SELECTOR).shouldBe(visible).getText();
    }

    public void openItem() {
        ROOT_ELEMENT.$(CLICKABLE_SELECTOR).shouldBe(visible).click();
    }

}