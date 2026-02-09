package com.practice.utils;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY_COUNT = 1; // Set how many times to retry

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { // Check if test failed
        	Throwable throwable = result.getThrowable();
        	if ((throwable != null) && (throwable instanceof StaleElementReferenceException || throwable instanceof ElementNotInteractableException)) {
            if (count < MAX_RETRY_COUNT) {
                count++;
                System.out.println("⚠️ Retrying " + result.getName() + " due to Selenium Exception: " + throwable.getClass().getSimpleName());;
                return true; // Tells TestNG to run the test again
            }
            }else {
				System.out.println("❌ Test failed due to: " + (throwable != null ? throwable.getClass().getSimpleName() : "Unknown reason") + ". Not retrying.");
			}
        }
        return false; // Tells TestNG to stop retrying
    }
}