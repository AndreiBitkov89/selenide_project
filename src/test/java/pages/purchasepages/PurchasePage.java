package pages.purchasepages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.cartpage.CartPage;
import valueObjects.Purchase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PurchasePage extends BasePage<PurchasePage> {

    private static final SelenideElement MODAL_WINDOW = $("#orderModalLabel");
    private static final SelenideElement INPUT_NAME = $("input#name");
    private static final SelenideElement INPUT_COUNTRY = $("input#country");
    private static final SelenideElement INPUT_CITY = $("input#city");
    private static final SelenideElement INPUT_CARD = $("input#card");
    private static final SelenideElement INPUT_MONTH = $("input#month");
    private static final SelenideElement INPUT_YEAR = $("input#year");
    private static final SelenideElement CONFIRM_BUTTON = $(By.xpath("//button[text()='Purchase']"));

    @Override
    protected void load() {
        Allure.step("Open Order modal window", ()-> CartPage.goToOrder());
    }

    @Override
    protected void isLoaded() {
        Allure.step("Check that modal window is opened", ()-> MODAL_WINDOW.shouldBe(visible));

    }

    public PurchasePage fillForm(Purchase purchase) {
        Allure.step("Fill form of order", () -> {
            INPUT_NAME.sendKeys(purchase.getNAME());
            INPUT_COUNTRY.sendKeys(purchase.getCOUNTRY());
            INPUT_CITY.sendKeys(purchase.getCITY());
            INPUT_CARD.sendKeys(purchase.getCARD());
            INPUT_MONTH.sendKeys(purchase.getMONTH());
            INPUT_YEAR.sendKeys(purchase.getYEAR());
        });
        return this;
    }

    public void submitPurchase() {
        Allure.step("Submit purchase", () -> {
                    CONFIRM_BUTTON.shouldBe(visible).click();
                }

        );

    }
}
