package com.apiTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import util.ConfigReader;

public class BaseTestApi {

    @BeforeClass
    public void precondition() {
        RestAssured.baseURI = ConfigReader.getProperty("base.URI");
    }


}
