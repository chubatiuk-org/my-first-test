package com.api;

import com.testdata.ApiDataProvider;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserApi extends BaseTestApi{

    private int createdUserId;

    @Test(dataProvider = "userData", dataProviderClass = ApiDataProvider.class)
    public void createUser(String name, String gender, String email, String status) {
        Logger log = LoggerFactory.getLogger(CreateUserApi.class);

        log.info("Step 1: Create new user via API");

        String requestBody = """
                {
                  "name": "%s",
                  "gender": "%s",
                  "email": "%s",
                  "status": "%s"
                }
                """.formatted(name, gender, email, status);

        createdUserId = given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201)
                .body(
                        "name", equalTo(name),
                        "email", equalTo(email),
                        "gender", equalTo(gender),
                        "status", equalTo(status),
                        "id", notNullValue()
                )
                .extract()
                .path("id");

        log.info("Step 2: Get user by Id={}", createdUserId);

        given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
            .when()
                .get("/users/{id}", createdUserId)
            .then()
                .log().all()
                .statusCode(200)
                .body(
                        "id", equalTo(createdUserId),
                        "name", equalTo(name),
                        "email", equalTo(email),
                        "gender", equalTo(gender),
                        "status", equalTo(status)
                );
    }

    @Test(dataProvider = "userDataUpdate", dataProviderClass = ApiDataProvider.class)
    public void updateUser(String updatedName, String updatedEmail, String updatedStatus) {
        Logger log = LoggerFactory.getLogger(CreateUserApi.class);

        log.info("Step 3: Update user with Id={}", createdUserId);

        String requestBodyUpdate = """
                {
                  "name": "%s",
                  "email": "%s",
                  "status": "%s"
                }
                """.formatted(updatedName, updatedEmail, updatedStatus);

        given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBodyUpdate)
        .when()
                .patch("/users/{id}", createdUserId)
        .then()
                .log().all()
                .statusCode(200)
                .body(
                        "id", equalTo(createdUserId),
                        "name", equalTo(updatedName),
                        "email", equalTo(updatedEmail),
                        "status", equalTo(updatedStatus)
                );

        log.info("Step 4: Get Updated user by Id={}", createdUserId);

        given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
            .when()
                .get("/users/{id}", createdUserId)
            .then()
                .log().all()
                .statusCode(200)
                .body(
                        "id", equalTo(createdUserId),
                        "name", equalTo(updatedName),
                        "email", equalTo(updatedEmail),
                        "status", equalTo(updatedStatus)
                );

        log.info("Step 5: Delete user with Id={}", createdUserId);

        given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
                .when()
                .delete("/users/{id}", createdUserId)
                .then()
                .log().all()
                .statusCode(204);

        log.info("Step 6: Verify user with Id={} is deleted", createdUserId);

        given()
                .header("Authorization", getAuthToken())
                .accept(ContentType.JSON)
                .when()
                .get("/users/{id}", createdUserId)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Resource not found"));
    }
}
