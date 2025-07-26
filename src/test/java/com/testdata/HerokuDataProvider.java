package com.testdata;

import org.testng.annotations.DataProvider;

public class HerokuDataProvider {

    @DataProvider(name = "inputTest")
    public Object[][] inputData(){
        return new Object[][]{
                {"12345"},
                {"98787"},
                {"1"}
        };
    }

}
