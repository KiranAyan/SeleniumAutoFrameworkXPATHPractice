package xpathSelenium;

import com.practice.base.BaseTest;

import com.practice.utils.ConfigReader;
import com.practice.utils.TestDataProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class DownloadAndDelete extends BaseTest {

	/*@Test(priority = 3, groups = {"Regression"})
	public void verifyDownloadTest() {
	    practicePage.clickDownloadButton(); // Trigger the download
	    
	    String expectedFileName = "sample_file.pdf";
	    boolean isDownloaded = FileUtils.isFileDownloaded(expectedFileName, 20);
	    
	    softAssert.assertTrue(isDownloaded, "File was not downloaded within 20 seconds!");
	    softAssert.assertAll();
	}
	*/
	
	/*
	 * // Add this to your initializeDriver() method in BaseTest
ChromeOptions options = new ChromeOptions();
Map<String, Object> prefs = new HashMap<>();
prefs.put("download.default_directory", "C:\\your_project_path\\downloads");
prefs.put("download.prompt_for_download", false); // Disables the "Are you sure?" prompt
options.setExperimentalOption("prefs", prefs);

rawDriver = new ChromeDriver(options);
	 */
		/*  import java.io.File;
import java.nio.file.Files;

public class FileUtils {

    /**
     * Deletes any file in the Downloads folder that matches the prefix and extension.
     * @param prefix The start of the filename (e.g., "sample_file")
     * @param extension The file extension (e.g., ".pdf")
     */
    /*public static void deleteExistingFiles(String prefix, String extension) {
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
}*/

}