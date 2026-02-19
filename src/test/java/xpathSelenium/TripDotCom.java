package xpathSelenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Durations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

	public class TripDotCom  implements WebDriverListener {
		public static WebDriver driver;
		public void setUp() {
		ChromeOptions options = new ChromeOptions();
		// Create a map to store browser preferences
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("profile.default_content_setting_values.geolocation", 2);
		prefs.put("download.default_directory", "C:\\CustomDownloadPath");
		prefs.put("download.prompt_for_download", false); // Skip the "Save As" popup
		options.setExperimentalOption("prefs", prefs);
		// This is the key argument for Incognito
	    options.addArguments("--incognito");
	    /* 1 by 1 this way also 
	     * // Disable notifications
options.addArguments("--disable-notifications");

// Alternatively, disable location popups
options.addArguments("--disable-geolocation");
	     */
	    
	    
		driver = new ChromeDriver(options);
		}
		public void launchBrowser() {
			setUp();
			driver.manage().window().maximize();
			driver.get("https://in.trip.com/");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			By elemnt = By.xpath("//i[contains(@class, 'close-icon')]");
			WebElement closeButton= wait.until(ExpectedConditions.visibilityOfElementLocated(elemnt));
			closeButton.click();
		}
		public void selectFullDate(int day, String month, String year) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        // 1. Open Calendar if not already open
	        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='checkInInput']"))).click();
	        String targetMonthYear = month + " " + year; // e.g., "April 2026"
	        // 2. Identify Month Header and Next Button based on your HTML
	        By currentMonthHeader = By.xpath("//div[@class='c-calendar-month__title']");
	        By monthHeader = By.xpath("//div[@class='c-calendar-month__title']");
	        By nextButton = By.xpath("//span[contains(@class,'c-calendar-icon-next')]");
	        String displayedMonthYear;
	        // 3. Navigation Loop
	        boolean found = false;
	        do {
	            // Using findElements to check both visible months in the split view
	            java.util.List<WebElement> titles = driver.findElements(monthHeader);
	            for (WebElement t : titles) {
	            	displayedMonthYear = t.getText();
	            	System.out.println(displayedMonthYear);
	                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
	                    found = true;
	                    break;
	                }
	            }if (!found) {
	                driver.findElement(nextButton).click();	                try { Thread.sleep(800); } 
	                catch (InterruptedException e) {} 
	            }
	        }
	        while (!found); // Keep doing this WHILE the month is NOT found
	        // 4. Final Selection using the stable XPath we discussed        
	        String dayXpath = "//div[@class='c-calendar-month'][descendant::div[text()='"+targetMonthYear+"']]//span[@class='day' and text()='"+day+"']";
	        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", dayElement);
	        System.out.println("Selected Date: " + day + " " + targetMonthYear);
	    }
		public void selectCity() {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			 WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='destinationInput']")));
			 cityInput.click();
			 cityInput.sendKeys("delhi");
			 By citySuggestion = By.xpath("//div[@class= '_V1Uz8Vssti1qpSpbuBo']//div[@class='FbD7EoBf7qumS36j5mD9']");
			 List<WebElement>suggestion = new ArrayList<>();
			 suggestion = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(citySuggestion));
			 for(WebElement s:suggestion){
			  System.out.println(s.getText());	 
			 }
			 //div[@class= 'FbD7EoBf7qumS36j5mD9']//div[@class='Vjur3ofifIvlu0MMq9zA']}
			 try { Thread.sleep(1500); } catch (InterruptedException e) { }
			 By cityOption = By.xpath("//i[@class='smarticon u-icon u-icon-ic_new_bu_flight u-icon_ic_new_bu_flight']/following-sibling::div//div[text()='Indira Gandhi International Airport(DEL)']");
			 wait.until(ExpectedConditions.elementToBeClickable(cityOption)).click();
		}
		 @Override
		    public void beforeFindElement(WebDriver driver, By locator) {
			 try {
		            List<WebElement> popups = driver.findElements(By.id("random-discount-close"));
		            if (!popups.isEmpty() && popups.get(0).isDisplayed()) {
		                popups.get(0).click();
		                System.out.println("Intercepted pop-up before finding: " + locator);
		            }
		        } catch (Exception e) {
		            // Ignore errors here to avoid crashing the main search
		        }
		        }
		 
		 public boolean verifyFlightsAreSortedByCheapest() {
			By searchbutton = By.xpath("//span[@class='tripui-online-btn-icon']/parent::div/parent::button");
			driver.findElement(searchbutton).click();
			List<Integer>priceListOriginal = new ArrayList<>();
			 List<Integer>priceListSorted;
			if(driver.getTitle().contains("Delhi Hotels - Where to stay in Delhi | Trip.com")){
				WebDriverWait Dwait = new WebDriverWait(driver, Duration.ofSeconds(10));
				Dwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='btn-txt' and text()='Check Availability']")));
				List<WebElement> hotelPriceList =  driver.findElements(By.xpath("//span[@class='sale']"));
				System.out.println(hotelPriceList.size());
				System.out.println(hotelPriceList.get(0).getText());
				for(WebElement price: hotelPriceList) {
					int everyHotelPrice= Integer.parseInt(price.getText().replaceAll("[^0-9]",""));  
					priceListOriginal.add(everyHotelPrice);
			}
			}
				System.out.println(priceListOriginal);
				//List<Integer>priceListSorted = new ArrayList<>(priceListOriginal);
				//Collections.sort(priceListSorted);
				priceListSorted =priceListOriginal.stream().sorted().toList();
				
			 return	priceListOriginal.equals(priceListSorted);
			 
		 }
		 public void selectRoomsandGuests() {
			By room = By.xpath("//div[text()='1 room, 2 adults, 0 children']");
			driver.findElement(room).click(); 
			By addAdult = By.xpath("//span[@class='c-room-guest__option-name' and text()='Adults']/following-sibling::div//button[@class='c-room-guest__option-btn c-room-guest__option-btn--add']"); 
			}

	public void tearDown() {
		driver.quit();
		}
	}
