package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class PostUserTest {

    @Test
    public void createUserTest() {
        // Using httpbin.org - reliable for POST testing
        RestAssured.baseURI = "https://httpbin.org";

        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/post") // This endpoint echoes the data and returns 200 OK
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        // httpbin.org returns 200 for successful posts
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");

        // Verify the echo
        String name = response.jsonPath().getString("json.name");
        Assert.assertEquals(name, "morpheus", "Name does not match");
    }
}