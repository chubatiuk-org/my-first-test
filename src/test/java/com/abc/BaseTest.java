package com.abc;

import browser.DriverManager;
import util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public abstract class BaseTest {

    public static final String PATH_TO_UPLOAD = "src/test/resources/upload";
    //public static final String PATH_TO_DOWNLOAD = "src/test/resources/download";
    public static final String PATH_TO_DOWNLOAD = ConfigReader.getProperty("download.path");

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