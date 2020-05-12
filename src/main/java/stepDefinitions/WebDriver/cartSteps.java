package stepDefinitions.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.cartPage;

public class cartSteps {
    private World world;
    public static WebDriver driver;
    public cartPage cartPage;

    public cartSteps(World world) {
        this.world = world;
        driver = (WebDriver) this.world.context.get("driver");
        cartPage = new cartPage(driver);
    }

    @Then("the item is displayed in their cart")
    public void theItemIsDisplayedInTheirCart() {
        cartPage.assertItemObjectExists();
    }


    @And("the shopper deletes the item from their cart")
    public void theShopperDeletesTheItemFromTheirCart() {
        cartPage.clickRemove();
    }

    @Then("the cart is now empty")
    public void theCartIsNowEmpty() {
        cartPage.assertNoItemsInCart();
    }
}
