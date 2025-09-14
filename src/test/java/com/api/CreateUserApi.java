package com.api;

import com.testdata.ApiDataProvider;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.ConfigReader;

import static io.restassured.RestAssured.given;

public class CreateUserApi extends BaseTestApi{

    private final String TOKEN = ConfigReader.getProperty("bearer.token");

    private int createdUserId;

    @Test(dataProvider = "userData", dataProviderClass = ApiDataProvider.class)
    public void createUser(String name, String gender, String email, String status) {
        Logger log = LoggerFactory.getLogger(CreateUserApi.class);

        log.info("Step 1: Create new user via API");

        UserRequest requestBody = new UserRequest(name, gender, email, status);

        UserResponse userResponse =
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(UserResponse.class);

        createdUserId = userResponse.getId();

        Assert.assertEquals(userResponse.getName(), requestBody.getName());
        Assert.assertEquals(userResponse.getEmail(), requestBody.getEmail());
        Assert.assertEquals(userResponse.getGender(), requestBody.getGender());
        Assert.assertEquals(userResponse.getStatus(), requestBody.getStatus());


        log.info("Step 2: Get user by Id={}", createdUserId);

        UserResponse createdUser =
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
            .when()
                .get("/users/{id}", createdUserId)
            .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        Assert.assertEquals(createdUser.getId(), createdUserId);
        Assert.assertEquals(createdUser.getName(), requestBody.getName());
        Assert.assertEquals(createdUser.getEmail(), requestBody.getEmail());
        Assert.assertEquals(createdUser.getGender(), requestBody.getGender());
        Assert.assertEquals(createdUser.getStatus(), requestBody.getStatus());
}

    @Test(dataProvider = "userDataUpdate", dataProviderClass = ApiDataProvider.class)
    public void updateUser(String updatedName, String updatedGender, String updatedEmail, String updatedStatus) {
        Logger log = LoggerFactory.getLogger(CreateUserApi.class);

        log.info("Step 3: Update user with Id={}", createdUserId);

        UserRequest requestBodyUpdate = new UserRequest(updatedName, updatedGender, updatedEmail, updatedStatus);

        UserResponse updatedUserResponse =
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBodyUpdate)
        .when()
                .patch("/users/{id}", createdUserId)
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        Assert.assertEquals(updatedUserResponse.getId(), createdUserId);
        Assert.assertEquals(updatedUserResponse.getName(), updatedName);
        Assert.assertEquals(updatedUserResponse.getGender(), updatedGender);
        Assert.assertEquals(updatedUserResponse.getEmail(), updatedEmail);
        Assert.assertEquals(updatedUserResponse.getStatus(), updatedStatus);

        log.info("Step 4: Get Updated user by Id={}", createdUserId);

        UserResponse updatedUser =
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
            .when()
                .get("/users/{id}", createdUserId)
            .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        Assert.assertEquals(updatedUser.getId(), createdUserId);
        Assert.assertEquals(updatedUser.getName(), updatedName);
        Assert.assertEquals(updatedUser.getGender(), updatedGender);
        Assert.assertEquals(updatedUser.getEmail(), updatedEmail);
        Assert.assertEquals(updatedUser.getStatus(), updatedStatus);

        log.info("Step 5: Delete user with Id={}", createdUserId);

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
        .when()
                .delete("/users/{id}", createdUserId)
        .then()
                .log().all()
                .statusCode(204);

        log.info("Step 6: Verify user with Id={} is deleted", createdUserId);

        String deleteMessage =
        given()
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
        .when()
                .get("/users/{id}", createdUserId)
        .then()
                .log().all()
                .statusCode(404)
                .extract()
                .path("message");

        Assert.assertEquals(deleteMessage, "Resource not found");
    }
}
