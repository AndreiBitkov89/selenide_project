package selenide.tests;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.SeverityLevel.*;

public class RegistrationTests extends BaseTest {

    Faker faker = new Faker();
    String testUser = "TestNameUser";
    String testPass = "TestPass";

    @Test
    @Severity(CRITICAL)
    void shouldRegisterClientAndAuthorize() {

        String name = faker.name().username();
        String pass = faker.internet().password();

        registrationPage.registration(name, pass, "Sign up successful.");
        loginPage.login(name, pass);

        mainPage.shouldShowWelcome(name);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterRegWithExistedCreds() {

        registrationPage.registration(testUser, testPass, "This user already exist.");
        registrationPage.getModal().shouldBe(visible);

    }

    @Test
    @Severity(CRITICAL)
    void errorAfterRegWithEmptyCreds() {

        registrationPage.registration("", "", "Please fill out Username and Password.");
        registrationPage.getModal().shouldBe(visible);

    }


}
