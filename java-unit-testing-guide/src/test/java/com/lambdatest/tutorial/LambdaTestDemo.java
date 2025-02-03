package com.lambdatest.tutorial;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaTestDemo {

    private static final String USERNAME = System.getenv("LT_USERNAME");
    private static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    private static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    @ParameterizedTest(name = "Browser: {0} v{1} - Simple Form Test")
    @CsvSource({
        "chrome,latest",
        "chrome,114.0",
        "firefox,latest",
        "firefox,113.0"
    })
    public void verifySimpleFormInputAndDisplay(String browser, String version) throws Exception {
        WebDriver driver = null;
        try {
            driver = createDriver(browser, version, "Form Input Tests", 
                                "Simple Form - " + browser + " " + version);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
            
            WebElement messageInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("user-message"))
            );
            messageInput.sendKeys("Test Message");
            
            WebElement showButton = driver.findElement(By.id("showInput"));
            showButton.click();
            
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("message"))
            );
            
            assertTrue(message.isDisplayed(), "Message should be displayed");
            assertEquals("Test Message", message.getText(), "Message text should match input");
            
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @ParameterizedTest(name = "Browser: {0} v{1} - E-commerce Search")
    @CsvSource({
        "chrome,latest",
        "firefox,latest"
    })
    public void verifyEcommerceSearchFunctionality(String browser, String version) throws Exception {
        WebDriver driver = null;
        try {
            driver = createDriver(browser, version, "E-commerce Search Tests", 
                                "Product Search - " + browser + " " + version);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://ecommerce-playground.lambdatest.io/");
            
            // Find and use the search box
            WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("search"))
            );
            searchBox.clear();
            searchBox.sendKeys("phone" + Keys.ENTER);
            
            // Wait for search results using a more reliable selector
            List<WebElement> searchResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector(".product-thumb")
                )
            );
            
            assertTrue(searchResults.size() > 0, "Search should return at least one product");
            
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void verifyCheckboxSelection() throws Exception {
        WebDriver driver = createDriver("chrome", "latest", "Checkbox Tests", 
                                      "Single Checkbox Test");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
            
            WebElement singleCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("isAgeSelected"))
            );
            singleCheckbox.click();
            
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("txtAge"))
            );
            
            assertTrue(message.isDisplayed(), "Success message should be displayed");
            
        } finally {
            driver.quit();
        }
    }

    private WebDriver createDriver(String browser, String version, String buildName, 
                                 String testName) throws Exception {
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", buildName);
        ltOptions.put("name", testName);
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("browserVersion", version);

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("LT:Options", ltOptions);
                return new RemoteWebDriver(URI.create(GRID_URL).toURL(), firefoxOptions);
                
            default: // chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("LT:Options", ltOptions);
                return new RemoteWebDriver(URI.create(GRID_URL).toURL(), chromeOptions);
        }
    }
} 