package stepDefinitions.hooks;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import stepDefinitions.WebDriver.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {

	private World world;
	private String testEnv = "dev";
	private WebDriver driver;

	public Hooks(World world) {
		this.world = world;
	}

	@Before("@Selenium")
	public void doSetupBeforeExecution() {
		Properties properties = new Properties();
		String browser;
		String url;
		String projectPath = System.getProperty("user.dir");

		try {
			properties.load(new FileInputStream(new File("./src/test/resources/config/global.properties")));
		} 	catch (IOException e) {
			e.printStackTrace();
		}

		browser = properties.getProperty("Browser");
		url = properties.getProperty("ebayURL");

		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					projectPath + "\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					projectPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					projectPath + "\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HashMap<String, String> map= new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			map.put((String) entry.getKey(), (String) entry.getValue());
		}
		this.world.context.put("utils", map);
		world.context.put("driver", driver);
	}

	@After("@Selenium")
	public void doCleanupAfterExecution(Scenario scenario){
		driver.quit();
	}
}
