package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userName;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillCredentialsAndClickLogin(String userNameValue, String passwordValue) {

        wait.until(ExpectedConditions.visibilityOf(userName));
        wait.until(ExpectedConditions.visibilityOf(password));
        wait.until(ExpectedConditions.visibilityOf(loginButton));

        userName.sendKeys(userNameValue);
        password.sendKeys(passwordValue);
        loginButton.click();
    }
}
