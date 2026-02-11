package xpathSelenium;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

	public class TripDotCom {
		WebDriver driver;
		public void setUp() {
		ChromeOptions options = new ChromeOptions();
		// Create a map to store browser preferences
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("profile.default_content_setting_values.geolocation", 2);
		options.setExperimentalOption("prefs", prefs);
		// This is the key argument for Incognito
	    options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		}
		public void launchBrowser() {

			// TODO Auto-generated method stub
			
			driver.manage().window().maximize();
			driver.get("https://in.trip.com/");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			By elemnt = By.xpath("//i[contains(@class, 'close-icon')]");
			WebElement closeButton= wait.until(ExpectedConditions.visibilityOfElementLocated(elemnt));
			closeButton.click();
		}
			public void dealCalendar(String monthYear, String fullDate) {
				LocalDate destinationDate = LocalDate.of(2026, 3, 15);

			    // 2. Format for the "While Loop" (Matches the text on screen)
			    String targetMonthYear = destinationDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));

			    // 3. Format for the "Direct Click" (Matches the data-date attribute)
			    String targetDate = destinationDate.toString();
			    // 1. Open Calendar
			    driver.findElement(By.xpath("//input[@id='checkInInput']")).click();

			    // 2. Navigate to correct Month
			    while (!driver.findElement(By.className("current-month-text")).getText().equalsIgnoreCase(monthYear)) {
			        driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
			    }

			    // 3. Click the specific date directly
			    // This finds the div where data-date='2026-03-15'
			    By finalDate = By.xpath("//div[@data-date='" + fullDate + "']");
			    
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			    wait.until(ExpectedConditions.elementToBeClickable(finalDate)).click();
			}
			//driver.quit();
	}

