package com.abc;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @FindBy(xpath = "//div[@id='flash']")
    private WebElement greenToaster;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutButton;

    @Test
    public void test5() throws InterruptedException {
        Logger log = LoggerFactory.getLogger(LoginTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/login");

        log.info("Step 2: Fill in credentials and click login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillCredentialsAndClickLogin("tomsmith","SuperSecretPassword!");

        log.info("Step 3: Verify Login is successful");
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(greenToaster));
        wait.until(ExpectedConditions.visibilityOf(logoutButton));

        Assert.assertTrue(greenToaster.isDisplayed(), "Green toaster with text \"You logged into a secure area!\" should be displayed");
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed");
    }
}
