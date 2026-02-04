package com.practice.listeners;

import com.practice.base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class MyListener extends BaseTest implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            // Take screenshot on failure
            takeScreenshot(result.getName());
            System.out.println("Screenshot captured for: " + result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}