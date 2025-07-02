package com.abc;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test
    public void test5() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(LoginTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state

        By userName = By.xpath("//input[@id='username']");
        By password = By.xpath("//input[@id='password']");
        By loginButton = By.xpath("//button[@type='submit']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        log.info("Step 2: Enter username");
        WebElement userNameElement = driver.findElement(userName);
        String userNameValue = "tomsmith";
        userNameElement.sendKeys(userNameValue);

        log.info("Step 3: Enter password");
        WebElement passwordElement = driver.findElement(password);
        String passwordValue = "SuperSecretPassword!";
        passwordElement.sendKeys(passwordValue);

        log.info("Step 4: Click Login button");
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();

        log.info("Step 5: Verify Login is successful");
        By greenToaster = By.xpath("//div[@id='flash']");
        By logoutButton = By.xpath("//a[@href='/logout']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(greenToaster));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));

        WebElement greenToasterText = driver.findElement(greenToaster);
        WebElement logoutButtonElement = driver.findElement(logoutButton);
        Assert.assertTrue(greenToasterText.isDisplayed(), "Green toaster with text \"You logged into a secure area!\" should be displayed");
        Assert.assertTrue(logoutButtonElement.isDisplayed(), "Logout button should be displayed");
    }
}
