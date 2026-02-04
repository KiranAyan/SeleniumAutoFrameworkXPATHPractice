package xpathSelenium;

import com.practice.base.BaseTest;
import com.practice.utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class PracticeXpath extends BaseTest {

    @BeforeMethod
    public void setup() {
        initializeDriver();
        driver.get(ConfigReader.getProperty("url"));
        
    }

    @Test
    public void practiceFormTest() throws InterruptedException {
    	System.out.println("Git Hub added and lets jenkin now");
        SoftAssert softAssert = new SoftAssert();
        System.out.println(System.getProperty("user.dir"));
        String title = driver.getTitle();
		System.out.println(title);
		WebElement menu = driver.findElement(By.xpath("//*[local-name()='svg' and contains(@onclick,'nameField()')]"));
		//input[@id='shub87'] for //svg[@onclick='nameField()']
		Assert.assertTrue(menu.isDisplayed(), " Search icon should be visible after page load.");
		softAssertFalseWithScreenshot(menu.isDisplayed(), "Search icon shouldn't be visible after page load.","SearchIconTest");
		//Fill form Now
		driver.findElement(By.xpath("//input[contains(@id,'shub')]")).sendKeys(ConfigReader.getProperty("username")); //By.xpath("//input[starts-with(@id,'shub')]")Css-//input[id^='shub']
		driver.findElement(By.id("pass")).sendKeys(ConfigReader.getProperty("password")); // css ---input#id.classname  and  input#pass.selectors-input.jsSelector
		driver.findElement(By.xpath("(//input[@placeholder='Enter your company'])[1]")).sendKeys(ConfigReader.getProperty("company"));
		
		//This Element is inside single shadow DOM.
		String cssSelectorForHost1 = "#userName";
		Thread.sleep(1000);
		SearchContext shadow = driver.findElement(By.cssSelector(cssSelectorForHost1)).getShadowRoot();
		Thread.sleep(1000);
		shadow.findElement(By.cssSelector("#kils")).sendKeys("yes");
		WebElement dowldLink = driver.findElement(By.linkText("DownLoad Link"));
		String url = dowldLink.getAttribute("href");
		System.out.println(url);
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
		softAssert.assertAll();

   /* @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
}