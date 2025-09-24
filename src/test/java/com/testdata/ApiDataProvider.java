package com.testdata;

import org.testng.annotations.DataProvider;

public class ApiDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][]{
                {"Monika Levinski1", "female", "monika1@qa4life.com", "active",
                        "Alyssa Milano01", "male", "alyssa01@qa4life.com", "inactive"},

                {"Monika Levinski2", "female", "monika2@qa4life.com", "active",
                        "Alyssa Milano02", "male", "alyssa02@qa4life.com", "inactive"}
        };
    }
}

