package com.abc;

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

public class InputTest extends BaseTest{

    @Test
    public void test4() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(InputTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/inputs");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state

        By input = By.xpath("//input[@type='number']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(input));

        log.info("Step 2: Make input empty");
        WebElement inputElement = driver.findElement(input);
        inputElement.clear();

        log.info("Step 3: Enter numbers");
        String numbers = "123";
        inputElement.sendKeys(numbers);

        log.info("Step 4: Verify numbers are entered");
        String actualValue = inputElement.getAttribute("value");
        Assert.assertEquals(actualValue, numbers, "Numbers should be entered");
    }
}
