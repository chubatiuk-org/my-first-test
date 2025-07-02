package com.abc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class UploadTest extends BaseTest{

    @Test
    public void test6() throws InterruptedException {

        Logger log = LoggerFactory.getLogger(UploadTest.class);

        //Clean the download directory before starting the test
        File downloadDir = new File(PATH_TO_DOWNLOAD);
        if (downloadDir.exists()) {
            for (File file : downloadDir.listFiles()) {
                if (!file.isDirectory()){
                    file.delete();
                }
            }
        }

        log.info("Step 1: Open website");
        driver.get("https://the-internet.herokuapp.com/upload");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state

        By chooseFileButton = By.cssSelector("input[type=file]");
        By uploadButton = By.id("file-submit");

        wait.until(ExpectedConditions.visibilityOfElementLocated(chooseFileButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadButton));

        log.info("Step 2: Add file to File Uploader page");
        String fileName = "someFile.txt";
        var pathToUpload = PATH_TO_UPLOAD + "/" + fileName;
        File uploadFile = new File(pathToUpload);

        WebElement chooseFileButtonElement = driver.findElement(chooseFileButton);
        chooseFileButtonElement.sendKeys(uploadFile.getAbsolutePath());

        Thread.sleep(Long.parseLong("2000"));// added for visibility

        log.info("Step 3: Upload file");
        WebElement uploadButtonElement = driver.findElement(uploadButton);
        uploadButtonElement.click();
        //driver.findElement(uploadButton).click();
        Thread.sleep(Long.parseLong("2000"));

        log.info("Step 4: Verify file is uploaded");
        By fileNameUploaded = By.id("uploaded-files");
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileNameUploaded));

        WebElement fileNameElement = driver.findElement(fileNameUploaded);
        Assert.assertEquals(fileName, fileNameElement.getText(), "File name should correspond to the selected file name");
        Thread.sleep(Long.parseLong("2000"));

        log.info("Step 5: Open website with downloaded files");
        driver.get("https://the-internet.herokuapp.com/download");
        Thread.sleep(Long.parseLong("2000")); // added for visibility of page initial state
        By uploadedFile = By.linkText(fileName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFile));

        log.info("Step 6: Verify tested file is present");
        WebElement testFile = driver.findElement(uploadedFile);
        Assert.assertTrue(testFile.isDisplayed(), "Tested file should be displayed");

        log.info("Step 7: Download tested file");
        testFile.click();

        log.info("Step 8: Verify file is downloaded");
        File downloadedFile = new File(PATH_TO_DOWNLOAD + "/" + fileName);
        int attempts = 0;

        while (attempts < 10) {
            if (downloadedFile.exists() && downloadedFile.isFile()) {
                break;
            }
            Thread.sleep(500);
            attempts++;
        }

        Assert.assertTrue(downloadedFile.exists(), "Downloaded file should exist in the download directory");
    }
}
