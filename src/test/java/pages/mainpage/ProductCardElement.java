package pages.mainpage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class ProductCardElement {

    private final SelenideElement ROOT_ELEMENT;
    private final String TITLE_SELECTOR = ".card-title a";
    private final String PRICE_SELECTOR = "h5";
    private final String DESCRIPTION_SELECTOR = "p.card-text";

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
        ROOT_ELEMENT.$(".card-title a").shouldBe(visible).click();
    }

}