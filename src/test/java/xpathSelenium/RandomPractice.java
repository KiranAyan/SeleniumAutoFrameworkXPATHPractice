package xpathSelenium;

import com.practice.base.BaseTest;

import com.practice.utils.ConfigReader;
import com.practice.utils.TestDataProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class RandomPractice extends BaseTest {

	@BeforeMethod
	public void setup() {
	    initializeDriver();
	    initSoftAssert(); // Initialize the SoftAssert from BaseTest
	    driver.get(ConfigReader.getProperty("url"));
	}

    @Test(dataProvider = "formData", dataProviderClass = TestDataProvider.class)
    public void practiceFormTest(String user, String pass, String company) throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	System.out.println("Git Hub added and lets jenkin now");
        System.out.println(System.getProperty("user.dir"));
        String title = driver.getTitle();
		System.out.println(title);
		WebElement menu = driver.findElement(By.xpath("//*[local-name()='svg' and contains(@onclick,'nameField()')]"));
		//input[@id='shub87'] for //svg[@onclick='nameField()']
		Assert.assertTrue(menu.isDisplayed(), " Search icon should be visible after page load.");
		softAssertFalseWithScreenshot(menu.isDisplayed(), "Search icon shouldn't be visible after page load.","SearchIconTest");
		//Fill form Now
		/*driver.findElement(By.xpath("//input[contains(@id,'shub')]")).sendKeys(ConfigReader.getProperty("username")); //By.xpath("//input[starts-with(@id,'shub')]")Css-//input[id^='shub']
		driver.findElement(By.id("pass")).sendKeys(ConfigReader.getProperty("password")); // css ---input#id.classname  and  input#pass.selectors-input.jsSelector
		driver.findElement(By.xpath("(//input[@placeholder='Enter your company'])[1]")).sendKeys(ConfigReader.getProperty("company"));*/
		driver.findElement(By.xpath("//input[contains(@id,'shub')]")).sendKeys(user); //By.xpath("//input[starts-with(@id,'shub')]")Css-//input[id^='shub']
		driver.findElement(By.id("pass")).sendKeys(pass); // css ---input#id.classname  and  input#pass.selectors-input.jsSelector
		driver.findElement(By.xpath("(//input[@placeholder='Enter your company'])[1]")).sendKeys(company);
		//This Element is inside single shadow DOM.
		String cssSelectorForHost1 = "#userName";
		Thread.sleep(1000);
		SearchContext shadow = driver.findElement(By.cssSelector(cssSelectorForHost1)).getShadowRoot();
		Thread.sleep(1000);
		shadow.findElement(By.cssSelector("#kils")).sendKeys("yes");
		/*WebElement dowldLink = driver.findElement(By.linkText("DownLoad Link"));
		String url = dowldLink.getAttribute("href");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dowldLink);
		//dowldLink.click();
		Actions action = new Actions(driver);

		// Example: Open in a New Tab (Control + Click)
		action.keyDown(Keys.CONTROL).click(dowldLink).keyUp(Keys.CONTROL).build().perform();

		// Example: Right Click (Context Click)
		action.contextClick(dowldLink).perform();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handleWindow = new ArrayList<>(windowHandles);
		
		for(String windowId : handleWindow) {
			driver.switchTo().window(windowId);
			System.out.println("Window ID is : " + windowId);
			System.out.println("Window Title is : " + driver.getTitle());
			
		}
	softAssert.assertAll();*/
		softAssert.assertAll();
    }
		@AfterMethod
		public void tearDown() { // No semicolon here!
		    if (driver != null) {
		        driver.quit();
		    }
		}
		

}