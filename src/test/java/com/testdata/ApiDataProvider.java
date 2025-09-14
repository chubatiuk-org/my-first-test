package com.testdata;

import org.testng.annotations.DataProvider;

public class ApiDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getUserData(){
        return new Object[][]{
                {"Monika Levinski77", "female", "monika77@qa4life.com", "active"}
        };
    }

    @DataProvider(name = "userDataUpdate")
    public Object[][] getUpdatedUserData(){
        return new Object[][]{
                {"Alyssa Milano88", "male", "alyssa88@qa4life.com", "inactive"}
        };
    }
}
