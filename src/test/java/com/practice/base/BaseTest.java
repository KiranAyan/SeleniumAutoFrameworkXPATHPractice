package com.practice.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.practice.utils.ConfigReader;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class BaseTest {
    public static WebDriver driver;
    public SoftAssert softAssert; 

    // 2. Initialize it here so it's fresh for every test
    @BeforeMethod
    public void initSoftAssert() {
        softAssert = new SoftAssert();
    }
    public void initializeDriver() {
    	String browserName = ConfigReader.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        int waitTime = Integer.parseInt(ConfigReader.getProperty("implicit.wait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
    }

    public String takeScreenshot(String testName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
    
 // Instead of just calling softAssert, create a helper method
    public void softAssertFalseWithScreenshot(boolean condition, String message, String testName) {
        // If the condition is TRUE, it means the 'assertFalse' will fail
        if (condition == true) { 
            try {
                takeScreenshot(testName + "_SoftFailure");
                System.out.println("Soft failure detected! Screenshot taken.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        softAssert.assertFalse(condition, message);
}
}