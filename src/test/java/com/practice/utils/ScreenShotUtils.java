package com.practice.utils;

import com.practice.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {

    public static String takeScreenshot(String testName) {
        // Access the static driver from BaseTest
        if (BaseTest.driver == null) {
            System.out.println("Screenshot skipped: Driver was not initialized.");
            return null;
        }

        try {
            // 1. Capture the screenshot as a file
            File src = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
            
            // 2. Define the destination path
            String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
            File destination = new File(path);
            
            // 3. Copy file to destination
            FileUtils.copyFile(src, destination);
            
            return path;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}