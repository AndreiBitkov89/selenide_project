package wrappers;

import com.codeborne.selenide.WebDriverRunner;
import enums.AlertType;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class CustomAlert implements Alert {

    private final AlertType expectedAlert;
    private Alert alert = null;

    public CustomAlert(AlertType expectedType) {
        this.expectedAlert = expectedType;

        try {
            WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5));
            this.alert = wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            Assertions.fail("Expected alert was not present. Type: " + expectedType);
            throw e;
        }
    }

    @Override
    public void dismiss() {
        alert.dismiss();
    }

    @Override
    public void accept() {
        String actualText = getText();
        if (!actualText.equals(expectedAlert.getMessage())) {
            Assertions.fail("Alert text mismatch: expected '" + expectedAlert.getMessage() + "', but was '" + actualText + "'");
        }
        alert.accept();
    }

    @Override
    public String getText() {
        return alert.getText();
    }

    @Override
    public void sendKeys(String keysToSend) {
        alert.sendKeys(keysToSend);
    }
}
