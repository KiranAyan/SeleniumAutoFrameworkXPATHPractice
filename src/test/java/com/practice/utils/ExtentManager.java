package com.practice.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private ExtentManager() {
    }
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Set the location of the report
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setOfflineMode(true); 
            spark.config().setTimelineEnabled(true);
            spark.config().setReportName("My Automation Results");
            spark.config().setDocumentTitle("Test Execution Report for Practice");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Kiran");
            extent.setSystemInfo("Module", "ERS");
        }
        return extent;
    }
}