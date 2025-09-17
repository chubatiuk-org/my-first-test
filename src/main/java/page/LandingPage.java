package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends Page {

    @FindBy(xpath = "//div[@id='flash']")
    private WebElement greenToaster;

    @FindBy(xpath = "//a[@href='/logout']")
    private WebElement logoutButton;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that green toaster is displayed")
    public boolean isGreenToasterDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(greenToaster));
        return greenToaster.isDisplayed();
    }

    @Step("Check that logout button is displayed")
    public boolean isLogoutButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        return logoutButton.isDisplayed();
    }
}




