package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.List;

public class AlertListener implements WebDriverListener {

    private final By POPUP_LOCATOR = By.xpath("//p[text()='Close']"); 
    private static boolean isChecking = false; 

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        // 1. Check: Is the element we are looking for the Pop-Up?
        if (locator.equals(POPUP_LOCATOR)) {
            
            // 2. If we are already inside the "handling" logic, stop here 
            // to prevent the infinite loop crash.
            if (isChecking) {
                return;
            }

            try {
                isChecking = true; // Close the gate
                
                // 3. Perform the HTML Handle logic
                List<WebElement> popups = driver.findElements(POPUP_LOCATOR);
                if (!popups.isEmpty() && popups.get(0).isDisplayed()) {
                    popups.get(0).click();
                    System.out.println("✅ Explicitly handled HTML Modal");
                }
            } catch (Exception e) {
                // Handle error
            } finally {
                isChecking = false; // Open the gate
            }
        }
    }
}