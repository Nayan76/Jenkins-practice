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
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1") // <--- Add this line
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");

        // 5. Verify Response Body Values
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        Assert.assertEquals(name, "morpheus", "Name does not match");
        Assert.assertEquals(job, "leader", "Job does not match");

        System.out.println("User Created Successfully: " + response.asString());
    }
}