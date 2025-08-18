package com.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import util.ConfigReader;

public class BaseTestApi {

    private final String TOKEN = ConfigReader.getProperty("bearer.token");

    @BeforeClass
    public void precondition() {
        RestAssured.baseURI = ConfigReader.getProperty("base.URI");
    }

    protected String getAuthToken() {
        return "Bearer " + TOKEN;
    }
}
