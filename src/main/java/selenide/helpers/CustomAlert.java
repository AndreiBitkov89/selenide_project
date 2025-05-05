package selenide.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import static com.codeborne.selenide.WebDriverRunner.*;
public class CustomAlert implements org.openqa.selenium.Alert {

    private final AlertTypes expectedType;
    private final Alert alert;

    public CustomAlert(AlertTypes expectedType) {
        this.expectedType = expectedType;

        long timeout = System.currentTimeMillis() + 2000;
        Alert tempAlert = null;

        while (System.currentTimeMillis() < timeout) {
            try {
                tempAlert = getWebDriver().switchTo().alert();
                break;
            } catch (NoAlertPresentException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }
        }

        if (tempAlert == null) {
            throw new AssertionError("Expected alert was not present. Type: " + expectedType);
        }

        this.alert = tempAlert;
    }


    @Override
    public void dismiss() {
        alert.dismiss();

    }

    @Override
    public void accept() {
        String actualText = alert.getText();
        if (!actualText.equals(expectedType.getMessage())) {
            throw new AssertionError("Expected: '" + expectedType.getMessage() + "' but was: '" + actualText + "'");
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
