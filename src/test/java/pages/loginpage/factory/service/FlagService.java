package pages.loginpage.factory.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FlagService {
    public static String getLoginVariant() {
        Response response = RestAssured.get("https://api.example.com/feature-flags");
        return response.jsonPath().getString("login_variant");
    }
}
