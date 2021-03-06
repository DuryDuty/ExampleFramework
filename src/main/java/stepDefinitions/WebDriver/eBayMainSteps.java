package stepDefinitions.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.eBayMainPage;
import utils.ReusableFunctions;

public class eBayMainSteps {
    private World world;
    public static WebDriver driver;
    public eBayMainPage mainPage;
    ReusableFunctions rfunc;

    public eBayMainSteps(World world) {
        this.world = world;
        driver = (WebDriver) this.world.context.get("driver");
        mainPage = new eBayMainPage(driver);
        rfunc = new ReusableFunctions(driver);
    }

    @Given("A shopper searches for {string}")
    public void aShopperSearchesFor(String arg0) {
        mainPage.enterSearch("Books");
        mainPage.clickSearch();
    }

    @Then("the shopper is shown a list of books, and clicks first item")
    public void theShopperIsShownAListOfBooksAndClicksFirstItem() {
        mainPage.clickFirstItem();
    }
}
