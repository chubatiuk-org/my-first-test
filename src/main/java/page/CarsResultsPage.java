package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarsResultsPage extends Page {

    public CarsResultsPage(WebDriver driver) {
        super(driver);
    }

    private static By firstResult = By.cssSelector("a.vehicle-card-link");

    public String getFirstResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstResult));

        WebElement firstResultElement = driver.findElement(firstResult);
        return firstResultElement.getText();
    }

    public boolean firstResultContains(String text) {
        return getFirstResult().toLowerCase().contains(text.toLowerCase());
    }

    public static void clickFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstResult));
        WebElement firstResultElement = driver.findElement(firstResult);
        firstResultElement.click();
    }
}


