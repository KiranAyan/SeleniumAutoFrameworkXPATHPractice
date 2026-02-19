package com.practice.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import com.practice.utils.ElementHelper;
import com.practice.utils.WaitUtils;

public class PracticePage {
    private WebDriver driver;

    // 1. Private Locators (Encapsulation)
    private By menuIcon = By.xpath("//*[local-name()='svg' and contains(@onclick,'nameField()')]");
    private By userNameField = By.xpath("//input[contains(@id,'shub')]");
    private By passwordField = By.id("pass");
    private By companyField = By.xpath("(//input[@placeholder='Enter your company'])[1]");
    private By shadowHost = By.cssSelector("#userName");
    private ElementHelper elementHelper;
    // 1. Private Locators (Encapsulation)
    private By dwldLocator = By.linkText("DownLoad Link");

    // 2. Constructor to receive driver from Test class
    public PracticePage(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
    }

    // 3. Action Methods
    public boolean isMenuIconVisible() {
        WaitUtils.waitForElementVisible(driver, menuIcon, 10);
        return driver.findElement(menuIcon).isDisplayed();
    }

    public void fillRegistrationForm(String user, String pass, String company) {
        driver.findElement(userNameField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(pass);
        try {
            driver.findElement(companyField).sendKeys(company);
        } catch (NoSuchElementException e) {
            System.out.println("Optional field 'Company' skipped.");
        }
    }

    public void fillShadowDomInput(String value) {
        SearchContext shadow = driver.findElement(shadowHost).getShadowRoot();
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        shadow.findElement(By.cssSelector("#kils")).sendKeys(value);
    }
    public String getDownloadUrl() {
        return driver.findElement(dwldLocator).getAttribute("href");
    }

    public void clickDownloadSafely() {
        // Using your ElementHelper with the timeout you specified
        elementHelper.safeClick(dwldLocator);
    }

    public void openInNewTab() {
        WebElement dowldLink = driver.findElement(dwldLocator);
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(dowldLink).keyUp(Keys.CONTROL).build().perform();
    }

    public void rightClickDownload() {
        WebElement dowldLink = driver.findElement(dwldLocator);
        new Actions(driver).contextClick(dowldLink).perform();
    }

    /**
     * Iterates through all windows, switches focus, and prints metadata.
     */
    public void switchAndPrintWindowDetails() {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handleWindow = new ArrayList<>(windowHandles);
        
        for(String windowId : handleWindow) {
            driver.switchTo().window(windowId);
            System.out.println("Window ID is : " + windowId);
            System.out.println("Window Title is : " + driver.getTitle());
        }
    }

}