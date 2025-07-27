package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    private By userName = By.xpath("//input[@id='username']");
    private By password = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillCredentialsAndClickLogin(String userNameValue, String passwordValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        driver.findElement(userName).sendKeys(userNameValue);
        driver.findElement(password).sendKeys(passwordValue);
        driver.findElement(loginButton).click();
    }
}
