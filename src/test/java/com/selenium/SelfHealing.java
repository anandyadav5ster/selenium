package com.selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelfHealing extends BaseTest{
    
    GenericFunctions gf;
    BussinessFunction bf;

    @BeforeMethod
    public void initializeGenericFunctions() {
        gf = new GenericFunctions(driver);
        bf = new BussinessFunction(driver);
        String url = GenericFunctions.readPropertyFile("url");
        driver.get(url);
    }

    @Test
    public static void enterUsername(){
          boolean isEntered = false;
       for (By locator : getUsernameLocators()) {
        
            try {
                System.out.println("Attempting to find element using: " + locator.toString());
                
                // Use a short explicit wait for each attempt
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

                if (element.isDisplayed()) {
                    element.clear();
                    element.sendKeys("automation_user");
                    System.out.println("SUCCESS: Found element using " + locator.toString());
                    isEntered = true;
                    break; // Exit loop once the element is found and handled
                }
            } catch (Exception e) {
                System.out.println("FAILED: Could not find element with " + locator.toString() + ". Trying next...");
            }
        }

        if (!isEntered) {
            org.testng.Assert.fail("CRITICAL: None of the provided locators for 'Username' were found on the page.");
        }
    }

    public static List<By> getUsernameLocators(){
        List<By>usernameLocator = new ArrayList<>();
        usernameLocator.add(By.cssSelector("#usernam"));
        usernameLocator.add(By.xpath("(//input[@type='text'])[1]"));
        usernameLocator.add(By.xpath("(//input[@placeholder='Enter Name'])[1]"));
        return usernameLocator;
    }
}
