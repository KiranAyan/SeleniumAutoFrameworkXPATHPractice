package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.List;

public class PopUpHandleListener implements WebDriverListener {

    // UPDATE THESE with the actual IDs or XPaths of the pop-up you see
    private final By POPUP_LOCATOR = By.id("at-cv-lightbox-close"); 
    private final By CLOSE_BUTTON = By.id("at-cv-lightbox-close");

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        // Use findElements (plural) to avoid an exception if the popup isn't there
        List<WebElement> popups = driver.findElements(POPUP_LOCATOR);
        
        if (!popups.isEmpty() && popups.get(0).isDisplayed()) {
            System.out.println("⚠️ [Listener] Popup detected! Clicking close...");
            try {
                popups.get(0).click();
                // Brief wait to let the animation finish
                Thread.sleep(500); 
            } catch (Exception e) {
                System.out.println("Could not close popup: " + e.getMessage());
            }
        }
    }
}