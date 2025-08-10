package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CarsPage extends Page {

    public CarsPage(WebDriver driver) {
        super(driver);
    }

    private SearchContext getShadowRoot() {
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("cars-search-form")));
        return shadowHost.getShadowRoot();
    }

    private WebElement getMakeDropdownElement() {
        return getShadowRoot().findElement(By.cssSelector("spark-select[label='Make']"));
    }

    private SearchContext getMakeDropdownShadowRoot() {
        return getMakeDropdownElement().getShadowRoot();
    }

    private WebElement getMakeValueElement(String makeValue) {
        return getMakeDropdownShadowRoot().findElement(By.cssSelector("option[value='%s']".formatted(makeValue.toLowerCase())));
    }

    public boolean isMakeOptionDisplayed(String makeValue) {
        return getMakeValueElement(makeValue).isDisplayed();
    }

    public void selectMake(String makeValue) {
        WebElement dropdownMakeElement = getMakeDropdownElement();
        dropdownMakeElement.sendKeys(makeValue);
        dropdownMakeElement.sendKeys(Keys.ENTER);
    }

    public boolean isMakeSelected(String makeValue) {
        return getMakeValueElement(makeValue).isSelected();
    }

    private WebElement getModelDropdownElement() {
        return getShadowRoot().findElement(By.cssSelector("spark-select[label='Model']"));
    }
    private SearchContext getModelDropdownShadowRoot() {
        return getModelDropdownElement().getShadowRoot();
    }

    private WebElement getModelValueElement(String make, String model) {
        String formattedModelValue = "%s-%s".formatted(make, model);
        return getModelDropdownShadowRoot().findElement(By.cssSelector("option[value='%s']".formatted(formattedModelValue.toLowerCase())));
    }

    public boolean isModelOptionDisplayed(String make, String model) {
        wait.until(ExpectedConditions.visibilityOf(getModelValueElement(make, model)));
        return getModelValueElement(make, model).isDisplayed();
    }

//    public List<String> getModelOptions(){
//        List<WebElement> options = shadowRootDropdownModel.findElements(By.cssSelector("option[part='option']"));
//        List<String> modelOptions = new ArrayList<>();
//        for (WebElement option : options) {
//            modelOptions.add(option.getText());
//        }
//        return modelOptions;
//    }

    public void selectModel(String make, String model) {
        getModelValueElement(make, model).click();
    }

    public boolean isModelSelected(String make, String model) {
        return getModelValueElement(make, model).isSelected();
    }

    private WebElement getShowButton() {
        return getShadowRoot().findElement(By.cssSelector("spark-button[type='submit']"));
    }

    public void clickShow() {
        getShowButton().click();
    }
}




