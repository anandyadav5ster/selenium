package com.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // private static WebDriver driver;
// ThreadLocal container to hold the driver instance for each thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
     public WebDriver getDriver() {
        return driver.get();
    }


    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        
        // Priority: Maven CLI (-Dbrowser) > TestNG XML > Default (chrome)
        String mvnBrowser = System.getProperty("browser");
        if (mvnBrowser != null) {
            browser = mvnBrowser;
        }

        System.out.println("Thread ID: " + Thread.currentThread().getId() + " - Starting: " + browser);

        WebDriver localDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser '" + browser + "' is not supported!");
        }

        // Set the driver in the ThreadLocal container for this specific thread
        driver.set(localDriver);

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public static void quitDriver() {
        
       try {
        if (driver != null) {
            Thread.sleep(200);
            driver.quit();
            driver = null;
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Driver quit interrupted", e);
    }
    }

    public String getBase64Screenshot(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
}
}
