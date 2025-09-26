package gorestAPI;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class CreateUpdateUserApi {
    private static final String USERS = "/users";
    private static final String USER_BY_ID = "/users/{id}";
    private final String token;

    public CreateUpdateUserApi(String token) {
        this.token = token;
    }

    @Step("Create user")
    public UserResponse createUser(UserRequest requestBody) {
        return given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(USERS)
                .then()
                .log().all()
                .statusCode(SC_CREATED)
                .extract()
                .as(UserResponse.class);
    }

    @Step("Get user by id")
    public UserResponse getUserById(int createdUserId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
            .when()
                .get(USER_BY_ID, createdUserId)
            .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(UserResponse.class);
    }

    @Step("Update user")
    public UserResponse updateUser(int createdUserId, UserRequest requestBodyUpdate) {
        return given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBodyUpdate)
                .when()
                .patch(USER_BY_ID, createdUserId)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(UserResponse.class);
    }

    @Step("Delete user")
    public void deleteUser(int createdUserId) {
        given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .when()
                .delete(USER_BY_ID, createdUserId)
                .then()
                .log().all()
                .statusCode(SC_NO_CONTENT);

    }
    @Step("Verify user is deleted")
    public String verifyUserIsDeleted(int createdUserId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
                .when()
                .get(USER_BY_ID, createdUserId)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .path("message");
    }
}