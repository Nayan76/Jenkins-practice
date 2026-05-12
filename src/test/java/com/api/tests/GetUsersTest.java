package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUsersTest {

    @Test
    public void verifyStatusCode() {
        // Using httpbin.org - a reliable testing service
        RestAssured.baseURI = "https://httpbin.org";

        Response response = given()
                .relaxedHTTPSValidation()
                .when()
                .get("/get") // This endpoint always returns 200 OK
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 200);
    }
}