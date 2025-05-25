package pages.cart;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuccessPurchasePage {
    private final SelenideElement MODAL = $(".sweet-alert");
    private final SelenideElement CONFIRM = MODAL.$("button.confirm");
    private final SelenideElement THANK_YOU_TEXT = $(By.xpath("//h2[starts-with(text(), \"Thank you\")]"));

    public SelenideElement getModal() {
        return MODAL;
    }

    public SelenideElement getConfirmButton() {
        return CONFIRM;
    }

    public SelenideElement getThankYouText() {
        return THANK_YOU_TEXT;
    }
}
