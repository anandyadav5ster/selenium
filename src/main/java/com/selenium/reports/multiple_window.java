package com.selenium;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;




public class multiple_window extends BaseTest{

    static WebDriver driver;
 
    @Test
    public void multiple_window_test(){
        driver = DriverFactory.getDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[text() = 'New Tab']")).click();
        String pw = driver.getWindowHandle();
        Set<String> allwin = driver.getWindowHandles();
        for(String win : allwin){
            if(!win.equalsIgnoreCase(pw)){
                driver.switchTo().window(win);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(pw);
        System.out.println(driver.getTitle());
        driver.close();
    }
}
