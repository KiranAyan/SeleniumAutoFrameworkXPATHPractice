package xpathSelenium;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.practice.base.BaseTest;
import com.practice.pages.PracticePage;

public class JScriptPopUpPractice extends BaseTest{
	PracticePage practicePage;
	@BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        initSoftAssert();
        driver.get("https://the-internet.herokuapp.com/entry_ad");
    }

	@Test(groups = {"Regression"})
	public void jsAlert() throws InterruptedException {
		driver.findElement(By.cssSelector("a#restart-ad")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#restart-ad")).click();
	}
}
