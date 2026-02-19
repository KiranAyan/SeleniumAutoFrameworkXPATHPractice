package com.practice.utils;

import com.practice.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {

	public static String takeScreenshot(String testName) {
	    if (BaseTest.driver == null) {
	        return null;
	    }

	    try {
	        // Create folder if it doesn't exist
	        String folderName = "ExtentReports/screenshots";
	        File directory = new File(System.getProperty("user.dir") + "/" + folderName);
	        if (!directory.exists()) directory.mkdirs();

	        File src = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
	        
	        String fileName = testName + "_" + System.currentTimeMillis() + ".png";
	        String fullPath = System.getProperty("user.dir") + "/" + folderName + "/" + fileName;
	        
	        // This is what the HTML report uses to find the image in the same folder
	        String relativePath = folderName + "/" + fileName;

	        File destination = new File(fullPath);
	        FileUtils.copyFile(src, destination);
	        
	        // Return the relative path for the Report
	        return relativePath; 
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}