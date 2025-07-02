package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropdownTest extends BaseTest{

    @Test
    public void test3() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(DropdownTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Thread.sleep(Long.parseLong("2000")); // added for visibility

        By dropdown = By.xpath("//select[@id='dropdown']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));

        log.info("Step 2: Expand dropdown list");
        WebElement dropdownElement = driver.findElement(dropdown);
        Select select = new Select(dropdownElement);

        log.info("Step 3: Verify both options are displayed");
        By option1 = By.xpath("//option[@value=\"1\"]");
        By option2 = By.xpath("//option[@value=\"2\"]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(option1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(option2));

        WebElement option1Element = driver.findElement(option1);
        WebElement option2Element = driver.findElement(option2);
        Assert.assertTrue(option1Element.isDisplayed(), "Option 1 should be displayed");
        Assert.assertTrue(option2Element.isDisplayed(), "Option 2 should be displayed");

        log.info("Step 4: Select Option 2");
        select.selectByValue("2");

        log.info("Step 5: Verify Option 2 is selected");
        Assert.assertTrue(option2Element.isSelected(), "Option 2 should be selected");
    }
}
