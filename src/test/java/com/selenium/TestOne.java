package com.selenium;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.utils.AssertUtil;

@Listeners(ITestListners.class)
public class TestOne extends BaseTest {

    GenericFunctions gf;
    BussinessFunction bf;

    @BeforeMethod
    public void initializeGenericFunctions() {
        gf = new GenericFunctions(driver);
        bf = new BussinessFunction(driver);
        String url = gf.readPropertyFile("url");
        driver.get(url);
    }

    @Test
    @SuppressWarnings("static-access")
    public void TestOne() {
        test.info("Starting TestOne execution");
        
        String title = driver.getTitle();
        test.info("Page title retrieved: " + title);
        
        AssertUtil.verifyEquals(title, "Automation Testing Practice", test, "Title validation failed");
      
        test.pass("Title validation successful");
        System.out.println("Test executed successfully");
        
        test.info("Entering text in name field");
        gf.enterText("id","name","Test");
        
        test.info("Selecting country from dropdown");
        WebElement countrydd = gf.createWebElement("id","country");
        gf.selectCountryDropdown(countrydd);
        
        test.info("Scrolling to Drag and Drop section");
        gf.scrollToElement("xpath","//h2[text()='Drag and Drop']");
        
        test.info("Uploading multiple files");
        bf.uploadMultipleFiles("id","multipleFilesInput");
        
        test.pass("TestOne completed successfully");
    }
 @Test
    @SuppressWarnings("static-access")
    public void TestTwo() {
        String title = driver.getTitle();
        AssertUtil.verifyEquals(title, "Automation Testing Practice", test, "Title validation failed");
      
        System.out.println("Test executed successfully");
        gf.enterText("id","name","Test");
        WebElement countrydd = gf.createWebElement("id","country");
        gf.selectCountryDropdown(countrydd);
    }
}
