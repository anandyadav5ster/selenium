package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BussinessFunction {

    static WebDriver driver;
    GenericFunctions gf;

    public BussinessFunction(WebDriver driver) {
        this.driver = driver;
        gf = new GenericFunctions(driver);
    }

    public void uploadMultipleFiles(String locatorType, String value){
        String file1 = System.getProperty("user.dir") + "/TestData/test1.csv";
        String file2 = System.getProperty("user.dir") + "/TestData/test2.csv";
        WebElement element = gf.createWebElement(locatorType, value);
        element.sendKeys(file1+"\n"+file2);
        gf.clickElement("xpath","//button[@type='submit']");

    }
    
}
