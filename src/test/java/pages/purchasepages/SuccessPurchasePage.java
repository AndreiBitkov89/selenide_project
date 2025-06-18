package pages.purchasepages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuccessPurchasePage {
    //todo верхний регистр
    private final SelenideElement MODAL = $(".sweet-alert");
    //todo MODAL.$() - такого в перменной я пока не видела)
    private final SelenideElement CONFIRM = MODAL.$("button.confirm");
    private final SelenideElement THANK_YOU_TEXT = $(By.xpath("//h2[starts-with(text(), \"Thank you\")]"));

    //todo геттеры на SelenideElement ?
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
