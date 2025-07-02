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
import java.util.List;

import static org.testng.internal.MethodHelper.isEnabled;

public class ButtonTest extends BaseTest{

    @Test
    public void test1() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(ButtonTest.class);

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state

        By addButton = By.xpath("//button[.='Add Element']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));

        log.info("Step 2: Click Add Button");
        WebElement addButtonElement = driver.findElement(addButton);
        wait.until(ExpectedConditions.elementToBeClickable(addButtonElement));
        addButtonElement.click();

        Thread.sleep(Long.parseLong("2000"));

        log.info("Step 3: Verify Add Button is clicked");

        By deleteButton = By.xpath("//button[.='Delete']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(deleteButton)));
        //wait.until(Boolean -> driver.findElement(deleteButton).isDisplayed());

        WebElement deleteButtonElement = driver.findElement(deleteButton);
        Assert.assertTrue(deleteButtonElement.isDisplayed(), "Delete button should be displayed");

        log.info("Step 4: Click Delete Button");
        deleteButtonElement.click();

        log.info("Step 5: Verify Delete Button is clicked");
        List<WebElement> deleteButtons = driver.findElements(deleteButton); //подсказал чат джпт, давай тоже обсудим
        Assert.assertTrue(deleteButtons.isEmpty(), "Delete button should not be present after click");
    }
}
