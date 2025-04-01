package selenide.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;

public class LoginTests extends BaseTest {

    String testUser = "TestNameUser";
    String testPass = "TestPass";
    Faker faker = new Faker();

    @Test
    @Severity(CRITICAL)
    void successfulLogin() {
        loginPage.login(testUser, testPass);
        loginPage.getModal().shouldBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginInvalidCreds() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        loginPage.fakeLogin(name, pass, "User does not exist.");
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    public void errorAfterLoginEmptyCreds() {

        loginPage.fakeLogin("", "", "Please fill out Username and Password.");
        mainPage.getUsernameAfterLogin().shouldNotBe(visible);

    }


}
