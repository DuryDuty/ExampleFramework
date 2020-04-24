package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.ReusableFunctions;

public class eBayMainPage {
    ReusableFunctions rfunc;
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement txtSearch;

    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement btnSearch;

    @FindBy(xpath = "(//li[@class='s-item    '])[1]//div[@class='s-item__info clearfix']/a")
    private WebElement linkFirstItem;

    public eBayMainPage(WebDriver driver){
        this.driver = driver;
        rfunc = new ReusableFunctions(driver);
        PageFactory.initElements(driver, this);
    }

    public eBayMainPage enterSearch(String query){
        rfunc.genericSendkeys(txtSearch,query);
        return this;
    }

    public eBayMainPage clickSearch(){
        rfunc.genericClick(btnSearch);
        return this;
    }

    public eBayMainPage clickFirstItem(){
        rfunc.explicitElementClick(linkFirstItem);
        return this;
    }
}
