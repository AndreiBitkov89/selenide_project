// todo декораторы тут не говорят ни о чем
package decorators;

import constants.AlertTypes;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

import static com.codeborne.selenide.WebDriverRunner.*;

public class CustomAlert implements Alert {

    //todo верхний регистр
    private final AlertTypes EXPECTED_ALERT;
    private final Alert ALERT;

    public CustomAlert(AlertTypes expectedType) {
        this.EXPECTED_ALERT = expectedType;
        //todo имя timeout не отражает сути переменной
        long timeout = System.currentTimeMillis() + 2000;
        Alert tempAlert = null;

        while (System.currentTimeMillis() < timeout) {
            try {
                tempAlert = getWebDriver().switchTo().alert();
                break;
            } catch (NoAlertPresentException e) {
                try {
                    //todo Selenide.sleep(100); ?
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (tempAlert == null) {
            //todo может быть падать с ошибкой Assertions.fail() ?
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
        //todo а почему проверка на соответствие только тут происходит, а не в ожидании нужного алерта?
        if (!actualText.equals(EXPECTED_ALERT.getMessage())) {
            //todo может быть падать с ошибкой Assertions.fail() ?
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
