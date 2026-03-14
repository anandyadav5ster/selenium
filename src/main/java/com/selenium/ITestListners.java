package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;

import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

public class ITestListners implements ITestListener {

     @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        WebDriver driver = getDriverInstance(result);
        
        if (test != null && driver != null) {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.pass("Test Passed - Success Screenshot", 
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        WebDriver driver = getDriverInstance(result);

        if (test != null && driver != null) {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.fail("Test Failed - Failure Screenshot", 
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            test.fail(result.getThrowable()); // Attaches the stacktrace
        }
    }

    // Helper to extract driver from the test class instance
    private WebDriver getDriverInstance(ITestResult result) {
        try {
            return (WebDriver) result.getTestClass().getRealClass()
                    .getField("driver").get(result.getInstance());
        } catch (Exception e) {
            return null;
        }
    }
}