package pages.loginPage.factory.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FlagService {
    private static final String BASE_URL = System.getProperty("flag.api.url", "https://api.example.com");

    public static String getLoginVariant() {
        Response response = RestAssured.get(BASE_URL + "/feature-flags");
        return response.jsonPath().getString("login_variant");
    }
}
