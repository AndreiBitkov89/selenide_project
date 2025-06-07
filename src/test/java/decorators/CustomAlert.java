package decorators;

import constants.AlertTypes;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import static com.codeborne.selenide.WebDriverRunner.*;
public class CustomAlert implements Alert {

    private final AlertTypes EXPECTED_ALERT;
    private final Alert ALERT;

    public CustomAlert(AlertTypes expectedType) {
        this.EXPECTED_ALERT = expectedType;
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

        this.ALERT = tempAlert;
    }


    @Override
    public void dismiss() {
        ALERT.dismiss();

    }

    @Override
    public void accept() {
        String actualText = ALERT.getText();
        if (!actualText.equals(EXPECTED_ALERT.getMessage())) {
            throw new AssertionError("Expected: '" + EXPECTED_ALERT.getMessage() + "' but was: '" + actualText + "'");
        }
        ALERT.accept();
    }

    @Override
    public String getText() {
        return ALERT.getText();
    }

    @Override
    public void sendKeys(String keysToSend) {
        ALERT.sendKeys(keysToSend);

    }
}
