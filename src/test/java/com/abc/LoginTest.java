package com.abc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LandingPage;
import page.LoginPage;

public class LoginTest extends BaseTest{

    @Test
    public void test5() {
        Logger log = LoggerFactory.getLogger(LoginTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/login");

        log.info("Step 2: Fill in credentials and click login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillCredentialsAndClickLogin("tomsmith","SuperSecretPassword!");

        log.info("Step 3: Verify Login is successful");
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isGreenToasterDisplayed(),"Green toaster with text \"You logged into a secure area!\" should be displayed");
        Assert.assertTrue(landingPage.isLogoutButtonDisplayed(),"Logout button should be displayed");
    }
}
