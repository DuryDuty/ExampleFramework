package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.ReusableFunctions;

public class itemPage {
    ReusableFunctions rfunc;
    private WebDriver driver;

    @FindBy(xpath = "//a[@id='atcRedesignId_btn']")
    private WebElement linkAddCart;

    @FindBy(xpath = "//span[text()='Go to cart']")
    private WebElement btnGoToCart;

    public itemPage(WebDriver driver){
        this.driver = driver;
        rfunc = new ReusableFunctions(driver);
        PageFactory.initElements(driver, this);
    }

    public itemPage clickAddCart(){
        rfunc.explicitElementClick(linkAddCart);
        return this;
    }

    public itemPage clickGoToCart(){
        rfunc.explicitElementClick(btnGoToCart);
        return this;
    }
}
