package gorestAPI;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;

public class CreateUpdateUserApi {
    private static final String USERS = "/users";
    private static final String USER_BY_ID = "/users/{id}";
    private final String token;

    public CreateUpdateUserApi(String token) {
        this.token = token;
    }

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
}
