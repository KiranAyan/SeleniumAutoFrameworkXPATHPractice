package xpathSelenium;

import com.practice.base.BaseTest;
import com.practice.pages.PracticePage;
import com.practice.utils.ConfigReader;
import com.practice.utils.TestDataProvider;
import org.testng.annotations.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class PracticeXpath1 extends BaseTest {
    
    PracticePage practicePage;

    @BeforeClass
    public void setupClass() {
        initializeDriver();
    }

    @BeforeMethod
    public void setupMethod() {
        initSoftAssert();
        driver.get(ConfigReader.getProperty("url"));
        practicePage = new PracticePage(driver); // Constructor call
    }
    @Test()
    public void checkUserisOnDashboard() {
    	test.info(MarkupHelper.createLabel("Test1", ExtentColor.BLUE).getMarkup() + " Checking menu icon");
        // 1. UI Check
        softAssertTrueWithScreenshot(practicePage.isMenuIconVisible(), "Menu missing", "IconTest");
    }

    @Test(dataProvider = "formData", dataProviderClass = TestDataProvider.class)
    public void fillForm(String user, String pass, String company) {
        // 2. Form Actions
        test.info("Filling registration form  TestCaseIds: TC0004, TC0005, TC0006");
        practicePage.fillRegistrationForm(user, pass, company);
    }
     @Test()
     public void dealShadow() {
        test.info("Entering Shadow DOM data");
        practicePage.fillShadowDomInput("yes");
        }
     @AfterClass
     public void assertionTest() {
        softAssert.assertAll();
        test.pass("Form submitted successfully!");
     }

    @AfterClass
    public void tearDown() {
        quitDriver();
    }
}