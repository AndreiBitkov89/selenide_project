package pages.cart;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import pages.BasePage;
import valueObjects.Purchase;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PurchasePage extends BasePage<PurchasePage> {

    private SelenideElement modalWindow = $("#orderModalLabel");
    private SelenideElement inputName = $("input#name");
    private SelenideElement inputCountry = $("input#country");
    private SelenideElement inputCity = $("input#city");
    private SelenideElement inputCard = $("input#card");
    private SelenideElement inputMonth = $("input#month");
    private SelenideElement inputYear = $("input#year");
    private SelenideElement confirmButton = $(By.xpath("//button[text()=\"Purchase\"]"));

    @Override
    protected void load() {
        CartPage.goToOrder();
    }

    @Override
    protected void isLoaded() throws Error {
        modalWindow.shouldBe(visible);
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
        confirmButton.shouldBe(visible).click();
    }
}
