package pages.mainpage;

import com.codeborne.selenide.SelenideElement;

public class ProductCardElement {

    private SelenideElement rootElement;

    public ProductCardElement(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getTitle() {
        return rootElement.$(".card-title a").getText().trim();
    }

    public int getPrice() {
        String priceText = rootElement.$("h5").getText().replace("$", "");
        return Integer.parseInt(priceText);
    }

    public String getDescription() {
        return rootElement.$("p.card-text").getText();
    }

    public void click() {
        rootElement.$("h4.card-title a").click();
    }
}