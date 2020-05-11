package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReusableFunctions {
    WebDriver driver;
    public ReusableFunctions(WebDriver driver){
        this.driver = driver;
    }

    public void genericClick(WebElement element) {
        element.click();
    }

    public void explicitElementClick(WebElement element) {
        makeExplicitWebElement(element).click();
    }

    public WebElement makeExplicitWebElement(WebElement element) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void genericSendkeys(WebElement element, CharSequence chars) {
        element.sendKeys(chars);
    }

    public void genericSelect(WebElement element, String option) {
        Select elementSelect = new Select(element);
        elementSelect.selectByVisibleText(option);
    }

    public void selectByValue(WebElement element, String option){
        Select elementSelect = new Select(element);
        elementSelect.selectByValue(option);
    }

    public void explicitElementSelect(WebElement element, String option){
        WebElement expElement = makeExplicitWebElement(element);
        Select elementSelect = new Select(expElement);
        elementSelect.selectByVisibleText(option);
    }

    public void assertExists(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }

    public static void genericCompare(String actualValue, String expectedValue){
        Assert.assertEquals(actualValue,expectedValue);
    }

    //Used for getting the text of the selected value from a combo box
    public String getSelected(WebElement element){
        Select elementSelct = new Select(element);
        WebElement option = elementSelct.getFirstSelectedOption();
        return option.getText();
    }

    //Get text from any element
    public String getText(WebElement element){
        return element.getText();
    }

    public String getValue(WebElement element){
        return element.getAttribute("value");
    }
}
