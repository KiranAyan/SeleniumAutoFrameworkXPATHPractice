package xpathSelenium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class SelectActions {
	SoftAssert st= new SoftAssert();

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();;
		driver.manage().window().maximize();
		/*driver.get("https://demoqa.com/select-menu");
		WebElement selectID = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));
		Select select=new Select(selectID);
		select.selectByValue("red");
		select.selectByVisibleText("Purple");
		select.selectByIndex(3);
		
		Actions actionsMouse = new Actions(driver);
		WebElement multiSelect = driver.findElement(By.xpath("//div[text()='Select Option']"));
		WebElement optionsArrow = driver.findElement(By.xpath("//div[text()='Select Option']/parent::div/following-sibling::div/div//*[local-name()='svg' and @width='20' and @viewBox='0 0 20 20']"));
		actionsMouse.moveToElement(optionsArrow).click().perform();
		actionsMouse.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();*/
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		driver.get("https://demoqa.com/upload-download");
		deleteExistingFiles("sampleFile", ".jpeg");
		WebElement downloadButton  = driver.findElement(By.xpath("//a[@id='downloadButton']"));
		downloadButton.click();
		boolean isFileDownloaded = isDownloaded();
		if(isFileDownloaded) {
			System.out.println("File downloaded successfully.");
		} else {
			System.out.println("File download failed.");
		}
		 driver.quit();
		
	}
	public static boolean  isDownloaded() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File dir = new File(downloadPath);
        File file = new File(downloadPath + "sampleFile.jpeg");

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (5 * 1000);

        while (System.currentTimeMillis() < endTime) {
            if (file.exists()) {
                // Check if file is still downloading (size might be 0 initially)
                if (file.length() > 0) {
                    return true;
                }
            }
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
	}
	public static void deleteExistingFiles(String prefix, String extension) {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File dir = new File(downloadPath);

        // Filter files that match your "wildcard" pattern
        File[] foundFiles = dir.listFiles((d, name) -> 
            name.startsWith(prefix) && name.endsWith(extension)
        );

        if (foundFiles != null && foundFiles.length > 0) {
            for (File file : foundFiles) {
                try {
                    boolean deleted = file.delete();
                    if (deleted) {
                        System.out.println("🗑️ Deleted existing file: " + file.getName());
                    }
                } catch (Exception e) {
                    System.err.println("Could not delete file: " + file.getName() + " - " + e.getMessage());
                }
            }
        } else {
            System.out.println("🧹 No existing files matching " + prefix + " found. Directory is clean.");
        }
    }


}
