package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ElementHelper {
    private WebDriver driver;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void safeClick(By locator) {
        try {
            WaitUtils.waitForElementVisible(driver, locator, 10).click();
        } catch (Exception e) {
            // Fallback to JavaScript Click if normal click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(locator));
        }
    }
}