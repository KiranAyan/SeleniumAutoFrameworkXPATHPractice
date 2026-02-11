package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class PopUpHandleListener implements WebDriverListener {

    // UPDATE THESE with the actual IDs or XPaths of the pop-up you see
    private final By POPUP_LOCATOR = By.id("at-cv-lightbox-close"); 

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
    	try {
            driver.switchTo().alert().accept();
            System.out.println("✅ Dismissed JavaScript Alert");
        } catch (Exception e) {
            // No JS alert, move on
        }

        // 2. Handle HTML/CSS Modals (The DOM Level)
        try {
            if (driver.findElements(POPUP_LOCATOR).size() > 0) {
                driver.findElement(POPUP_LOCATOR).click();
                System.out.println("✅ Closed HTML Modal");
                Thread.sleep(500); // Wait for fade-out animation
            }
        } catch (Exception e) {
            // No modal found or already closed
        }}
}