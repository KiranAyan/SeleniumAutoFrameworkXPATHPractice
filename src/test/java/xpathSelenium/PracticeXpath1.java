package xpathSelenium;

import com.practice.base.BaseTest;
import com.practice.pages.PracticePage;
import com.practice.utils.ConfigReader;
import com.practice.utils.TestCaseID;
import com.practice.utils.TestDataProvider;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.testng.annotations.*;

public class PracticeXpath1 extends BaseTest {
    
    PracticePage practicePage;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        initSoftAssert();
        driver.get(ConfigReader.getProperty("url"));
        practicePage = new PracticePage(driver); // Constructor call
    }
    @TestCaseID(id = "TC-101,TC-102,TC-110")
    @Test(groups = {"Sanity", "Regression","Smoke"})
    @Feature("Authentication") // Global Feature
    @Story("Login with valid credentials")
    public void checkUserisOnDashboard() {
    	//test.info(MarkupHelper.createLabel("Test1", ExtentColor.BLUE).getMarkup() + " Checking menu icon");
        // 1. UI Check
        softAssertTrueWithScreenshot(practicePage.isMenuIconVisible(), "Menu missing", "IconTest");
    }
    @TestCaseID(id = "TC-103,TC-104,TC-105")
    @Test(dataProvider = "formData", dataProviderClass = TestDataProvider.class,groups = {"Regression"})
    public void fillForm(String user, String pass, String company) {
        // 2. Form Actions
        test.info("Filling registration form  TestCaseIds: TC0004, TC0005, TC0006");
        practicePage.fillRegistrationForm(user, pass, company);
    }
    @TestCaseID(id = "TC-106,TC-107,TC-110")
     @Test(groups = {"Regression"})
     public void dealShadow() {
        test.info("Entering Shadow DOM data");
        practicePage.fillShadowDomInput("yes");
        }
    @AfterMethod(alwaysRun = true)
     public void assertionTest() {
        softAssert.assertAll();
        test.pass("Form submitted successfully!");
     }

  
}