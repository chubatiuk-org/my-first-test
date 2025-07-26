package browser;

import util.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;

public class DriverManager {

    public static WebDriver getDriver(String browserType) throws IllegalAccessException {
        var width = Integer.parseInt(ConfigReader.getProperty("dimension.width"));
        var height = Integer.parseInt(ConfigReader.getProperty("dimension.height"));

        return switch (browserType.toLowerCase()) {
            case "chrome" -> {
                var chromeOptions = new ChromeOptions();
                var map = new HashMap<String, Object>();

                map.put("profile.password_manager_leak_detection", false);
                map.put("download.default_directory", new File(ConfigReader.getProperty("PATH_TO_DOWNLOAD")).getAbsolutePath());

                chromeOptions.setExperimentalOption("prefs", map);

                WebDriver driver = new ChromeDriver(chromeOptions);

                driver.manage().window().setSize(new Dimension(width, height));

                yield driver;
            }
            case "firefox" -> {
                var firefoxOptions = new FirefoxOptions();

                firefoxOptions.addPreference("browser.download.folderList", 2); // Use custom location
                firefoxOptions.addPreference("browser.download.dir", new File(ConfigReader.getProperty("PATH_TO_DOWNLOAD")).getAbsolutePath());
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); // Change MIME type as needed
                firefoxOptions.addPreference("pdfjs.disabled", true); // Disable built-in PDF viewer
                firefoxOptions.addPreference("signon.rememberSignons", false); // Disable password manager


                WebDriver driver = new FirefoxDriver(firefoxOptions);

                driver.manage().window().setSize(new Dimension(width, height));

                yield driver;
            }
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        };
    }
}
