package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarsPage extends Page {

    public CarsPage(WebDriver driver) {
        super(driver);
    }

    private WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("cars-search-form")));
    SearchContext shadowRoot = shadowHost.getShadowRoot();

    private WebElement dropdownMake = shadowRoot.findElement(By.cssSelector("spark-select[label='Make']"));
    SearchContext shadowRootDropdownMake = dropdownMake.getShadowRoot();

    private WebElement getMakeValue(String makeValue) {
        return shadowRootDropdownMake.findElement(By.cssSelector("option[value='" + makeValue.toLowerCase() + "']"));
    }

    public boolean isMakeOptionDisplayed(String makeValue) {
        return getMakeValue(makeValue).isDisplayed();
    }

    public void selectMake(String makeValue) {
        //getMakeValue(makeValue).click();
        dropdownMake.sendKeys(makeValue);
        dropdownMake.sendKeys(Keys.ENTER);
    }

    public boolean isMakeSelected(String makeValue) {
        return getMakeValue(makeValue).isSelected();
    }

    private final WebElement dropdownModel = shadowRoot.findElement(By.cssSelector("spark-select[label='Model']"));
    SearchContext shadowRootDropdownModel = dropdownModel.getShadowRoot();

    private WebElement getModelValue(String modelValue) {
        return shadowRootDropdownModel.findElement(By.cssSelector("option[value='" + modelValue.toLowerCase() + "']"));
    }

    public boolean isModelOptionDisplayed(String modelValue) {
        wait.until(driver -> {
            getModelValue(modelValue);
            return true;
        });
        return getModelValue(modelValue).isDisplayed();
    }

    public void selectModel(String modelValue) {
        getModelValue(modelValue).click();
//        dropdownModel.sendKeys(modelValue);
//        dropdownModel.sendKeys(Keys.ENTER);
    }

    public boolean isModelSelected(String modelValue) {
        return getModelValue(modelValue).isSelected();
    }

    private WebElement buttonShow = shadowRoot.findElement(By.cssSelector("spark-button[type='submit']"));

    public void clickShow() {
        buttonShow.click();
    }
}




