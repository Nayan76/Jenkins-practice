package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUsersTest {
    // Update your test method
    @Test
    public void verifyStatusCode() {
        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .relaxedHTTPSValidation()
                .header("x-api-key", "reqres-free-v1") // <--- Add this line
                .when()
                .get("/users/2")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
    }
}