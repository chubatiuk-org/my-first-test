package com.browser;

import com.abc.BaseTest;
import com.util.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DriverManager {

    public static WebDriver getDriver(String browserType) throws IllegalAccessException {
        return switch (browserType.toLowerCase()) {
            case "chrome":
                var chromeOptions = new ChromeOptions();
                var map = new HashMap<String, Object>();

                map.put("profile.password_manager_leak_detection", false);
                map.put("download.default_directory", new File(BaseTest.PATH_TO_DOWNLOAD).getAbsolutePath());

                chromeOptions.setExperimentalOption("prefs", map);

                var driver = new ChromeDriver(chromeOptions);

                var width = Integer.parseInt(ConfigReader.getProperty("dimension.width"));
                var height = Integer.parseInt(ConfigReader.getProperty("dimension.height"));
                driver.manage().window().setSize(new Dimension(width, height));

                yield driver;

            case "firefox":
                yield new FirefoxDriver();
            default:
                throw new IllegalAccessException("Unsupported browser type: " + browserType);
        };
    }
}
