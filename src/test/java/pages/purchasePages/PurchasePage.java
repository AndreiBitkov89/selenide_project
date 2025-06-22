package pages.purchasePages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import valueObjects.Purchase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PurchasePage extends BasePage<PurchasePage> {

    private final SelenideElement modalWindow = $("#orderModalLabel");
    private final SelenideElement inputName = $("input#name");
    private final SelenideElement inputCountry = $("input#country");
    private final SelenideElement inputCity = $("input#city");
    private final SelenideElement inputCard = $("input#card");
    private final SelenideElement inputMonth = $("input#month");
    private final SelenideElement inputYear = $("input#year");
    private final SelenideElement confirmButton = $(By.xpath("//button[text()='Purchase']"));

    @Override
    protected void isLoaded() {
        Allure.step("Check that modal window is opened", () -> modalWindow.shouldBe(visible));
    }

    public PurchasePage fillForm(Purchase purchase) {
        Allure.step("Fill form of order", () -> {
            inputName.sendKeys(purchase.getName());
            inputCountry.sendKeys(purchase.getCountry());
            inputCity.sendKeys(purchase.getCity());
            inputCard.sendKeys(purchase.getCard());
            inputMonth.sendKeys(purchase.getMonth());
            inputYear.sendKeys(purchase.getYear());
        });
        return this;
    }

    public void submitPurchase() {
        Allure.step("Submit purchase", () -> {
                    confirmButton.click();
                }
        );

    }
}
