package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUsersTest {

    @Test
    public void verifyStatusCode() {
        // 1. Define the Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // 2. Send GET Request with SSL relaxation and API Key as Query Param
        Response response = given()
                .relaxedHTTPSValidation() // Bypasses SSL certificate checks
                .queryParam("api_key", "reqres-free-v1") // Passes API key as parameter
                .when()
                .get("/users/2")
                .then()
                .extract().response();

        // 3. Verify Status Code
        Assert.assertEquals(response.statusCode(), 200);

        System.out.println("Get User Response: " + response.asString());
    }
}