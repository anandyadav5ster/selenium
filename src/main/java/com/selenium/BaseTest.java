package com.selenium;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    public WebDriver getDriver() {
        return driver;
    }

      @BeforeSuite
    public void setUpReport() {
        // 1. Initialize the Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
        spark.config().setReportName("Automation Results");
        spark.config().setDocumentTitle("Test Report");

        // 2. Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Anand");
    }

    @BeforeMethod
    public void setup(Method method) {
        driver = DriverFactory.getDriver();

        // create test in extent
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test Failed: " + result.getName(), 
                    createScreenCaptureFromBase64String(base64Screenshot).build());
                test.fail(result.getThrowable());
                log.error("Test failed: " + result.getName() + ". Screenshot attached to report.");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed: " + result.getName(), 
                    createScreenCaptureFromBase64String(base64Screenshot).build());
                log.info("Test passed: " + result.getName());
            }
        if (driver != null) {
            // Capture screenshot for both success and failure
            

            log.info("Closing Browser instance.");
            driver.quit();
        }
        // No throws needed!
        DriverFactory.quitDriver();
    }

   @AfterSuite
    public void tearDownReport() {
        
        if (extent != null) {
            log.info("Flushing Extent Report to disk...");
            extent.flush();
        }
    }
}
