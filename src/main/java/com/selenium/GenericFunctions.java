package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.text.SimpleDateFormat;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import java.util.Set;

public class GenericFunctions {

     public static WebDriver driver;

    public GenericFunctions(WebDriver driver){
        this.driver = driver;

    }

     public static void handleAlert(){
     //      Wait for the alert to be present (Best Practice)
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
          wait.until(ExpectedConditions.alertIsPresent());
          
          // Switch focus to the alert
          Alert alert = driver.switchTo().alert();
          
          // Get the text from the alert
          System.out.println("Alert text: " + alert.getText());
          
          // Accept (Click OK)
          alert.accept();
          
          // Dismiss (Click Cancel - if applicable)
          // alert.dismiss();

          // Handling Prompt Alerts

          // Alert prompt = driver.switchTo().alert();
          // prompt.sendKeys("Selenium Automation");
          // prompt.accept();

     }

     public static void handleFrame(){
        //    Ways to Switch to a Frame

          // 1. By Index (Zero-based)
          driver.switchTo().frame(0);
          
          // 2. By ID or Name attribute
          driver.switchTo().frame("login-frame");
          
          // 3. By WebElement (Most flexible)
          WebElement frameElement = driver.findElement(By.cssSelector("iframe.modal-body"));
          driver.switchTo().frame(frameElement);
          
          
        //   Exiting a Frame
          
        //   After performing actions inside a frame, you must switch back to the main page to interact with elements outside the frame.
          
          // Switch back to the main document
          driver.switchTo().defaultContent();
          
          // Switch to the immediate parent frame (if frames are nested)
          driver.switchTo().parentFrame();

     }
     
     public static void handleMultipleWindow() {
          // 1. Store the ID of the original window
     String parentWindow = driver.getWindowHandle();
     
     // 2. Perform the action that opens a new window
     driver.findElement(By.id("new-tab-button")).click();
     
     // 3. Get all available window handles
     Set<String> allWindows = driver.getWindowHandles();
     
     // 4. Iterate through the set to find the new handle
     for (String handle : allWindows) {
         if (!handle.equals(parentWindow)) {
             // 5. Switch to the new window
             driver.switchTo().window(handle);
             System.out.println("Switched to: " + driver.getTitle());
             break; 
         }
}

// Perform actions in the new window...
driver.findElement(By.id("email")).sendKeys("test@example.com");

// 6. Close the child window and return to parent
driver.close(); 
driver.switchTo().window(parentWindow);
     }
     public static void getElementColor(){
          WebElement element = driver.findElement(By.id("login-button"));
          
          // Get text color
          String textColor = element.getCssValue("color");
          
          // Get background color
          String bgColor = element.getCssValue("background-color");
          
          System.out.println("Text Color (RGBA): " + textColor);

     }

     public static String readPropertyFile(String value) {
        try {
            FileInputStream fis = new FileInputStream("./config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            value = prop.getProperty(value);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return value;
    }

    public void takeScreenshot(String name) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        try {
             //interface called TakesScreenshot and getScreenshotAs(...)
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("./screenshots/" + name +"-"+ timeStamp+".png"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

     public static void handleShadowRoot(){
          WebElement shadowHost = driver.findElement(By.cssSelector("#shadow-host"));

            // 2. Get the Shadow Root
            // In Selenium 4, getShadowRoot() returns a SearchContext
            SearchContext shadowRoot = shadowHost.getShadowRoot();

            // 3. Find elements inside the Shadow Root
            // Note: Use only findElement (not findElements if you want a single item)
            // and remember that XPath is not supported here.
            WebElement insideElement = shadowRoot.findElement(By.cssSelector(".inner-button"));
            
            System.out.println("Text inside shadow: " + insideElement.getText());
            // insideElement.click();

     }
 
    public enum LocatorType {
        xpath, css;
    }

    public static void clickElement(String locatorType, String locatorValue) {
        switch(locatorType) {
            case "xpath":
                driver.findElement(By.xpath(locatorValue)).click(); 
                break;
            case "css":
                driver.findElement(By.cssSelector(locatorValue)).click();
        }
    }
    public static void enterText(String locatorType, String locatorValue, String text) {
        switch(locatorType) {
            case "xpath":
                driver.findElement(By.xpath(locatorValue)).sendKeys(text);
                break;
            case "css":
                driver.findElement(By.cssSelector(locatorValue)).sendKeys(text);
            case "id":
                driver.findElement(By.id(locatorValue)).sendKeys(text);
        }
    }

    public static WebElement createWebElement(String locatorType, String locatorValue) {
        WebElement WebElement;
        switch(locatorType) {
            case "xpath":
                WebElement = driver.findElement(By.xpath(locatorValue));
                break;
            case "css":
                WebElement = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "id":
                WebElement = driver.findElement(By.id(locatorValue));
                break;
            default:
                WebElement = driver.findElement(By.name(locatorValue));
        }
        return WebElement;
    }

    public static void selectCountryDropdown(WebElement element){
        Select countryVal = new Select(element);
        countryVal.selectByValue("india");
    }

    public static void scrollToElement(String locatorType, String locatorValue) {
        WebElement element = createWebElement(locatorType, locatorValue);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
  
}
