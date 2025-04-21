package selenide.webpages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuccessPurchasePage {
    private SelenideElement alertModal = $(".sweet-alert");
    private SelenideElement confirmButton = alertModal.$("button.confirm");
    private SelenideElement thanyouText = $(By.xpath("//h2[starts-with(text(), \"Thank you\")]"));

    public SelenideElement getAlertModal() {
        return alertModal;
    }

    public SelenideElement getConfirmButton() {
        return confirmButton;
    }

    public SelenideElement getThanyouText() {
        return thanyouText;
    }
}
