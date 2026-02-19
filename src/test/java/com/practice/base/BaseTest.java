package com.practice.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.practice.utils.ConfigReader;
import com.practice.utils.ExtentManager;
import com.practice.utils.PopUpHandleListener;
import com.practice.utils.ScreenShotUtils;
import com.practice.utils.TestCaseID;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BaseTest {
    public static WebDriver rawDriver;
    public static WebDriver driver;
    public SoftAssert softAssert; 
    public static ExtentReports extent;
    public ExtentTest test;
 // 2. Initialize the Report engine ONCE before the entire test suite starts
    @BeforeSuite(alwaysRun = true)
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    // 3. Create a new test entry in the report before EVERY @Test method starts
    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        test = extent.createTest(method.getName());
     // 3. REFLECTION LOGIC: Extract the TestCaseID
        if (method.isAnnotationPresent(Test.class)) {
            // We get the @Test annotation object from the method
            Test testAnnotation = method.getAnnotation(Test.class);
            String[] groups = testAnnotation.groups();
            
            for (String group : groups) {
                // This places "Smoke" or "Sanity" in the red circle area
                test.assignCategory(group.trim());
            }
        }
        if (method.isAnnotationPresent(TestCaseID.class)) {
            String testId = method.getAnnotation(TestCaseID.class).id();
            
            // CHANGE THIS LINE: 
            // Instead of test.info(), use assignCategory()
            String[] ids = testId.split(",");
            for(String id : ids) {
                test.assignCategory(id.trim());
            }
        }
    }
    
    @BeforeClass(alwaysRun = true)
	public void setupClass() {
	    initializeDriver(); // Opens browser once
	}
    // 2. Initialize it here so it's fresh for every test
   
    public void initSoftAssert() {
        softAssert = new SoftAssert();
    }
    public void initializeDriver() {
    	String browserName = ConfigReader.getProperty("browser");
    	System.out.println(ConfigReader.getProperty("browser"));

        if (browserName.equalsIgnoreCase("chrome")) {
        	rawDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
        	rawDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
        	rawDriver = new EdgeDriver();
        }
        
     // --- NEW: Wrap the driver with the Listener ---
        PopUpHandleListener popupListener = new PopUpHandleListener();
        driver = new EventFiringDecorator<>(popupListener).decorate(rawDriver);
        
        driver.manage().window().maximize();
        int waitTime = Integer.parseInt(ConfigReader.getProperty("implicit.wait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
    }
    
 // Instead of just calling softAssert, create a helper method
    public void softAssertFalseWithScreenshot(boolean condition, String message, String testName) {
        // If the condition is TRUE, it means the 'assertFalse' will fail
        if (condition == true) { 
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			
			// 2. Construct Filename: Name + Timestamp
			String fileName = testName + "_" + timestamp + "_SoftFailure";
			String screenshotPath = ScreenShotUtils.takeScreenshot(fileName);
			// 2. Log to Extent Report immediately
			test.fail(message, 
		            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			System.out.println("Soft failure detected! Screenshot taken.");
        }
        softAssert.assertFalse(condition, message);
}
    public void softAssertTrueWithScreenshot(boolean condition, String message, String testName) {
        // If the condition is TRUE, it means the 'assertFalse' will fail
        if (condition == false) { 
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			
			// 2. Construct Filename: Name + Timestamp
			String fileName = testName + "_" + timestamp + "_SoftFailure";
			String screenshotPath = ScreenShotUtils.takeScreenshot(fileName);
			test.fail(message, 
		            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			System.out.println("Soft failure detected! Screenshot taken.");
        }
        softAssert.assertTrue(condition, message);

}
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("Stopping the driver and cleaning up session...");
        quitDriver();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Essential for Retries!
            rawDriver = null; // Clean up the undecorated driver too
        }
    }
 // 4. Capture results and screenshots after EVERY @Test method ends
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenShotUtils.takeScreenshot(result.getName());
            
            // Log the error message and attach the screenshot
            test.fail("Test Failed: " + result.getThrowable().getMessage(), 
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } 
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } 
        else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }
    }

    // 5. Write all the data to the HTML file once the entire suite is finished
    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush(); // This generates the actual HTML file
        }
    }
    @SuppressWarnings("deprecation")
	@AfterSuite(alwaysRun = true)
    public void generateAllureReport() {
        try {
            // Simple command line execution from Java to copy the file
            Runtime.getRuntime().exec("cmd /c copy src\\test\\resources\\environment.properties target\\allure-results\\");
            System.out.println("Environment file attached to Allure.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}