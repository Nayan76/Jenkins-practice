package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostUserTest {

    @Test
    public void createUserTest() {
        // 1. Define the Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // 2. Define the Request Body (JSON payload)
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        // 3. Send the POST request and capture the response
        Response response = given()
                .header("Content-Type", "application/json") // Set Content-Type header
                .relaxedHTTPSValidation()
                .body(requestBody)                         // Attach the body
                .when()
                .post("/users")                            // Endpoint
                .then()
                .extract().response();

        // 4. Verify Status Code (201 Created)
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");

        // 5. Verify Response Body Values
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        Assert.assertEquals(name, "morpheus", "Name does not match");
        Assert.assertEquals(job, "leader", "Job does not match");

        System.out.println("User Created Successfully: " + response.asString());
    }
}