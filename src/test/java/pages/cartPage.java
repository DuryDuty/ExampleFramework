package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ReusableFunctions;

public class cartPage {
    ReusableFunctions rfunc;
    private WebDriver driver;

    @FindBy(xpath = "//button[@data-test-id='cart-remove-item']")
    private WebElement btnRemove;

    @FindBy(xpath = "//div[@data-test-id='cart-bucket']")
    private WebElement divCartItem;

    @FindBy(xpath = "//span[contains(text(),'was removed from your cart.')]")
    private WebElement spnNoItemsInCart;

    public cartPage(WebDriver driver){
        this.driver = driver;
        rfunc = new ReusableFunctions(driver);
        PageFactory.initElements(driver, this);
    }

    public cartPage assertItemObjectExists(){
        rfunc.assertExists(divCartItem);
        return this;
    }

    public cartPage clickRemove(){
        rfunc.genericClick(btnRemove);
        return this;
    }

    public cartPage assertNoItemsInCart(){
        rfunc.assertExists(spnNoItemsInCart);
        return this;
    }
}
