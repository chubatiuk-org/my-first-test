package com.abc;

import com.browser.DriverManager;
import com.util.ConfigReader;
import org.apache.commons.exec.CommandLine;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    public static final String PATH_TO_UPLOAD = "src/test/resources/upload";
    public static final String PATH_TO_DOWNLOAD = "src/test/resources/download";

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void precondition() throws IllegalAccessException {

        var browserType = ConfigReader.getProperty("browser");
        driver = DriverManager.getDriver(browserType);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        //driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));

//        final Map<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.password_manager_leak_detection", false);
//        final ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("prefs", chromePrefs);
    }

    @AfterClass
    public void postcondition() {
        try {
            Thread.sleep(Long.parseLong("2000"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (driver != null) {
            driver.close(); //only current tab if many tabs are opened
        }
    }
}

//ChromeOptions options = new ChromeOptions();
//options.addArguments("--headless=new");
//driver = new ChromeDriver(options);
//ctrl+alt+l - to format code
//driver.manage().window().setSize(new Dimension(1000, 800)); - размер окна для хрома