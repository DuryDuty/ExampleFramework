package stepDefinitions.WebDriver;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.itemPage;
import utils.ReusableFunctions;

public class itemSteps {
    private World world;
    public static WebDriver driver;
    public itemPage itemPage;
    ReusableFunctions rfunc;

    public itemSteps(World world) {
        this.world = world;
        driver = (WebDriver) this.world.context.get("driver");
        itemPage = new itemPage(driver);
        rfunc = new ReusableFunctions(driver);
    }

    @And("the shopper adds the first item to their cart")
    public void theShopperAddsTheFirstItemToTheirCart() throws InterruptedException {
        Thread.sleep(1500);
        itemPage.clickAddCart();
        itemPage.clickGoToCart();
    }
}
