package com.practice.listeners;

import com.practice.base.BaseTest;
import com.practice.utils.ScreenShotUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener extends BaseTest implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
    	System.err.println("❌ TEST FAILED: " + result.getName());
        System.err.println("❌ ERROR CAUSE: " + result.getThrowable());
        // Take screenshot on failure
		ScreenShotUtils.takeScreenshot(result.getName());
		System.out.println("Screenshot captured for: " + result.getName());
    }
    public void onTestSkipped(ITestResult result) {
        // not implemented
      }
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
      }
    public void onStart(ITestContext context) {
        // not implemented
      }
    public void onFinish(ITestContext context) {
        // not implemented
      }
    public void onTestSuccess(ITestResult result) {
        // not implemented
      }
}