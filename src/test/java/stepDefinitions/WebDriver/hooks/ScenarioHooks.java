package stepDefinitions.WebDriver.hooks;

import org.openqa.selenium.WebDriver;
import stepDefinitions.WebDriver.World;

public class ScenarioHooks {
    private World world;
    private WebDriver driver;

   public ScenarioHooks(World world) {
       driver = (WebDriver) world.context.get("driver");
       System.out.println(driver);
    }
}