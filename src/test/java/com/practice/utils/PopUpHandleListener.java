package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.List;

public class PopUpHandleListener implements WebDriverListener {

    private final By POPUP_LOCATOR = By.id("//p[text()='Close']"); 
    private static boolean isChecking = false; 

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        if (isChecking) return;

        try {
            isChecking = true;
            // 1. The locator you found
            By popupClose = By.xpath("//p[text()='Close']");
            List<WebElement> popups = driver.findElements(popupClose);

            if (!popups.isEmpty() && popups.get(0).isDisplayed()) {
                popups.get(0).click();
                System.out.println("✅ GLOBAL: Popup closed.");

                // 2. WAIT for the screen to be clear (The "Underlay" fix)
                // We wait until the underlay div is no longer visible/present
                long timeout = System.currentTimeMillis() + 3000;
                while (System.currentTimeMillis() < timeout) {
                    if (driver.findElements(By.className("underlay")).isEmpty()) {
                        break;
                    }
                    Thread.sleep(200); 
                }
            }
        } catch (Exception e) {
            // Handle cases where the popup vanishes on its own
        } finally {
            isChecking = false;
        }
    }
}