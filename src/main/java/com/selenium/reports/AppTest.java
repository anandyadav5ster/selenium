package com.selenium.reports;


import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.selenium.BaseTest;
import com.selenium.DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest
{
    static WebDriver driver;
    @Test
    public void ToolQA()
    {
    //   WebDriverManager.chromedriver().setup();
    //   driver = new ChromeDriver();
    String url = "https://toolsqa.com/selenium-webdriver/handle-dynamic-webtables-in-selenium-webdriver/";
    driver = DriverFactory.getDriver();
      driver.get(url);
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      WebElement table = driver.findElement(By.xpath("//table"));
      Actions action = new Actions(driver);
      action.moveToElement(table).perform();
    //   JavascriptExecutor js = (JavascriptExecutor)driver;
    //   js.executeScript("arguments[0].scrollIntoView(true);",table);
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.visibilityOf(table));
      List<WebElement> rows = table.findElements(By.tagName("tr"));
      for(int i = 1; i<rows.size();i++){
        List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
        for(int j = 0;j<cols.size();j++){
            String colText = cols.get(j).getText();
            if(colText.equalsIgnoreCase("QTP")){
                System.out.println("Row index "+(i));
                System.out.println("col index "+(j+1));
                break;
            }
        }
      }




    }

   
}
