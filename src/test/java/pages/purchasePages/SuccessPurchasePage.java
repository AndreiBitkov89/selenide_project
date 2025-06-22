package pages.purchasePages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SuccessPurchasePage {
    private final SelenideElement modal = $(".sweet-alert");
    private final SelenideElement confirm = modal.$("button.confirm");
    private final SelenideElement thankYouText = $(By.xpath("//h2[starts-with(text(), \"Thank you\")]"));


    public boolean isThankYouVisible() {
        return thankYouText.shouldBe(visible).isDisplayed();
    }
}
