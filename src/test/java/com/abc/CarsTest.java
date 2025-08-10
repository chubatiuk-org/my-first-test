package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CarsPage;
import page.CarsResultsPage;
import page.LoginPage;

public class CarsTest extends BaseTest{

    @Test
    public void testCars() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(CarsTest.class);

        log.info("Step 1: Open website");
        driver.get("https://www.cars.com/");

        String makeValue = "ford";
        String modelValue = "ranger";

        log.info("Step 2: Verify " + makeValue + " option is displayed in Make dropdown");
        CarsPage carsPage = new CarsPage(driver);
        Assert.assertTrue(carsPage.isMakeOptionDisplayed(makeValue), makeValue + " value should be displayed in Make dropdown");

        log.info("Step 3: Select " + makeValue + " value in Make dropdown");
        carsPage.selectMake(makeValue);

        log.info("Step 4: Verify %s value is selected in Make dropdown".formatted(makeValue));
        Assert.assertTrue(carsPage.isMakeSelected(makeValue), makeValue + " value should be selected in Make dropdown");

        log.info("Step 5: Verify %s option is displayed in Model dropdown".formatted(modelValue));
        Assert.assertTrue(carsPage.isModelOptionDisplayed(makeValue, modelValue), modelValue + " value should be displayed in Model dropdown");

        log.info("Step 6: Select " + modelValue + " value in Model dropdown");
        carsPage.selectModel(makeValue, modelValue);

        log.info("Step 7: Verify " + modelValue + " value is selected in Model dropdown");
        Assert.assertTrue(carsPage.isModelSelected(makeValue, modelValue), modelValue + " value should be selected in Model dropdown");

        log.info("Step 8: Click Show button");
        carsPage.clickShow();

        log.info("Step 9: Verify Search Results");
        CarsResultsPage resultsPage = new CarsResultsPage(driver);
        Assert.assertTrue(resultsPage.firstResultContains(makeValue + " " + modelValue), "First Result link should correspond to the selected make and model");

        log.info("Step 10: Click First Search Result");
        resultsPage.clickFirstResult();
    }
}
