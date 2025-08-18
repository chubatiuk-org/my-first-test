package com.testdata;

import org.testng.annotations.DataProvider;

public class ApiDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getUserData(){
        return new Object[][]{
                {"Monika Levinski3", "female", "monika3@qa4life.com", "active"}
        };
    }

    @DataProvider(name = "userDataUpdate")
    public Object[][] getUpdatedUserData(){
        return new Object[][]{
                {"Alyssa Milano", "alyssa@qa4life.com", "inactive"}
        };
    }
}
