package com.apiTest;

import gorestAPI.CreateUpdateUserApi;
import gorestAPI.UserRequest;
import gorestAPI.UserResponse;
import com.testdata.ApiDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.ConfigReader;


public class CreateUserApiTest extends BaseTestApi{

    private final String TOKEN = ConfigReader.getProperty("bearer.token");
    private int createdUserId;

    @Test(dataProvider = "userData", dataProviderClass = ApiDataProvider.class)
    public void createUser(String name, String gender, String email, String status) {
        Logger log = LoggerFactory.getLogger(CreateUserApiTest.class);

        log.info("Step 1: Create new user via API");

        UserRequest requestBody = new UserRequest(name, gender, email, status);

        CreateUpdateUserApi createUserApi = new CreateUpdateUserApi(TOKEN);
        UserResponse userResponse = createUserApi.createUser(requestBody);

        createdUserId = userResponse.getId();

        Assert.assertEquals(userResponse.getName(), requestBody.getName());
        Assert.assertEquals(userResponse.getEmail(), requestBody.getEmail());
        Assert.assertEquals(userResponse.getGender(), requestBody.getGender());
        Assert.assertEquals(userResponse.getStatus(), requestBody.getStatus());


        log.info("Step 2: Get user by Id={}", createdUserId);

        UserResponse createdUser = createUserApi.getUserById(createdUserId);

        Assert.assertEquals(createdUser.getId(), createdUserId);
        Assert.assertEquals(createdUser.getName(), requestBody.getName());
        Assert.assertEquals(createdUser.getEmail(), requestBody.getEmail());
        Assert.assertEquals(createdUser.getGender(), requestBody.getGender());
        Assert.assertEquals(createdUser.getStatus(), requestBody.getStatus());
}

    @Test(dataProvider = "userDataUpdate", dataProviderClass = ApiDataProvider.class)
    public void updateUser(String updatedName, String updatedGender, String updatedEmail, String updatedStatus) {
        Logger log = LoggerFactory.getLogger(CreateUserApiTest.class);

        log.info("Step 3: Update user with Id={}", createdUserId);

        UserRequest requestBodyUpdate = new UserRequest(updatedName, updatedGender, updatedEmail, updatedStatus);

        CreateUpdateUserApi createUserApi = new CreateUpdateUserApi(TOKEN);
        UserResponse updatedUserResponse = createUserApi.updateUser(createdUserId, requestBodyUpdate);

        Assert.assertEquals(updatedUserResponse.getId(), createdUserId);
        Assert.assertEquals(updatedUserResponse.getName(), updatedName);
        Assert.assertEquals(updatedUserResponse.getGender(), updatedGender);
        Assert.assertEquals(updatedUserResponse.getEmail(), updatedEmail);
        Assert.assertEquals(updatedUserResponse.getStatus(), updatedStatus);

        log.info("Step 4: Get Updated user by Id={}", createdUserId);

        UserResponse updatedUser = createUserApi.getUserById(createdUserId);

        Assert.assertEquals(updatedUser.getId(), createdUserId);
        Assert.assertEquals(updatedUser.getName(), updatedName);
        Assert.assertEquals(updatedUser.getGender(), updatedGender);
        Assert.assertEquals(updatedUser.getEmail(), updatedEmail);
        Assert.assertEquals(updatedUser.getStatus(), updatedStatus);

        log.info("Step 5: Delete user with Id={}", createdUserId);
        createUserApi.deleteUser(createdUserId);

        log.info("Step 6: Verify user with Id={} is deleted", createdUserId);
        String deleteMessage = createUserApi.verifyUserIsDeleted(createdUserId);

        Assert.assertEquals(deleteMessage, "Resource not found");
    }
}
