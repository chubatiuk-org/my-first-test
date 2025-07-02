package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckboxTest extends BaseTest{

    Logger log = LoggerFactory.getLogger(CheckboxTest.class);

    @Test
    public void test2() throws InterruptedException {

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state

        By checkbox1 = By.xpath("//input[position()=1]");
        By checkbox2 = By.xpath("//input[position()=2]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox1)); // вопрос на синк: как записать wait по-другому чтоб не перечислять каждый чекбокс
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox2));

        log.info("Step 2: Check the initial state of checkboxes");
        WebElement checkbox1Element = driver.findElement(checkbox1);
        WebElement checkbox2Element = driver.findElement(checkbox2);
        Assert.assertFalse(checkbox1Element.isSelected(), "Checkbox 1 should be UNchecked");
        Assert.assertTrue(checkbox2Element.isSelected(), "Checkbox 2 should be checked");

        log.info("Step 3: Click checkbox 1");
        checkbox1Element.click();

        log.info("Step 4: Verify the checkbox 1 is checked");
        Assert.assertTrue(checkbox2Element.isSelected(), "Checkbox 1 should be checked");
        Thread.sleep(Long.parseLong("2000")); //for visibility


        log.info("Step 5: Click checkbox 2");
        checkbox2Element.click();

        log.info("Step 6: Verify the checkbox 2 is UNchecked");
        Assert.assertFalse(checkbox2Element.isSelected(), "Checkbox 2 should be UNchecked");
    }
}
