package xpathSelenium;

import com.practice.base.BaseTest;
import com.practice.pages.PracticePage;
import com.practice.utils.ConfigReader;
import com.practice.utils.TestCaseID;

import org.testng.annotations.*;

public class PracticeXpath2 extends BaseTest  {
	 /*try {
		int bal = 100;
		if (bal<1010) {
			throw new Exception("BHai low balance h ");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}*/		
	PracticePage practicePage;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        initSoftAssert();
        driver.get(ConfigReader.getProperty("url"));
        practicePage = new PracticePage(driver); // Constructor call
    }
    
    @Test(priority = 0,groups = {"Sanity", "Regression"})
    public void downloadLinkTest() {
        test.info("Fetching the 'href' attribute from Download Link");
        String url = practicePage.getDownloadUrl();
        System.out.println("URL is: " + url);

        // 2. Perform Specialized Clicks
        test.info("Performing Right Click on Download Link");
        practicePage.rightClickDownload();
        test.pass("Right click action performed successfully.");
    }
        
        @TestCaseID(id = "TC-112")
        @Test(priority = 1,groups = {"Sanity", "Regression","Smoke"})
        public void openInNewTabTest() {

        test.info("Opening Download Link in a New Tab (Control + Click)");
        practicePage.openInNewTab();
		test.pass("Download link opened in new tab successfully.");
        }
        @TestCaseID(id = "TC-112")
        @Test(priority = 2,groups = {"Regression"})
        public void windowSwitchingTest() {
        test.info("Iterating through all open windows and switching focus");
        practicePage.switchAndPrintWindowDetails();

        test.pass("Download page interactions and window switching completed successfully.");
    }
    
}
