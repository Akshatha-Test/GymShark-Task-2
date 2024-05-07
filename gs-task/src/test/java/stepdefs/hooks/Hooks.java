package stepdefs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static utils.SeleniumCommands.getCommands;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

	public static WebDriver driver;

	public Hooks() {
		this.driver = driver;
	}

	@Before
	public void setup() throws Exception {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void clickOnElement(By selector) throws Exception {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(selector));
	}

	/*public void verifyElement(By selector) throws Exception {
		if (driver.findElements(selector).size() > 0) {
			System.out.println(selector + " is displayed on screen ");
			getCommands().waitForAndClickOnElementLocated(selector);
		} else {
			System.out.println(selector + " is not displayed on screen,please check the locator ");
		}
	}*/
}
