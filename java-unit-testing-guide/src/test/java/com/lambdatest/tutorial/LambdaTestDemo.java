package com.lambdatest.tutorial;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lambdatest.tutorial.config.TestConfig;

public class LambdaTestDemo extends BaseTest {

    @ParameterizedTest(name = "Browser: {0} v{1} - Simple Form Test")
    @CsvSource({
        TestConfig.Browser.CHROME + "," + TestConfig.Version.LATEST,
        TestConfig.Browser.CHROME + "," + TestConfig.Version.CHROME_114,
        TestConfig.Browser.FIREFOX + "," + TestConfig.Version.LATEST,
        TestConfig.Browser.FIREFOX + "," + TestConfig.Version.FIREFOX_113
    })
    public void verifySimpleFormInputAndDisplay(String browser, String version) throws Exception {
        setupTest(browser, version, "Form Input Tests", 
                 "Simple Form - " + browser + " " + version,
                 TestConfig.Platform.WINDOWS_10);
                 
        executeTest(() -> {
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
        });
    }

    @ParameterizedTest(name = "Browser: {0} v{1} - E-commerce Search")
    @CsvSource({
        TestConfig.Browser.CHROME + "," + TestConfig.Version.LATEST,
        TestConfig.Browser.FIREFOX + "," + TestConfig.Version.LATEST
    })
    public void verifyEcommerceSearchFunctionality(String browser, String version) throws Exception {
        setupTest(browser, version, "E-commerce Search Tests",
                 "Product Search - " + browser + " " + version,
                 TestConfig.Platform.WINDOWS_10);
                 
        executeTest(() -> {
            driver.get("https://ecommerce-playground.lambdatest.io/");
            
            WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("search"))
            );
            searchBox.clear();
            searchBox.sendKeys("phone" + Keys.ENTER);
            
            List<WebElement> searchResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector(".product-thumb")
                )
            );
            
            assertTrue(!searchResults.isEmpty(), "Search should return at least one product");
        });
    }

    @Test
    public void verifyCheckboxSelection() throws Exception {
        setupTest("chrome", "latest", "Checkbox Tests", 
                  "Single Checkbox Test",
                  TestConfig.Platform.WINDOWS_10);
                  
        executeTest(() -> {
            driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
            
            WebElement singleCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("isAgeSelected"))
            );
            singleCheckbox.click();
            
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("txtAge"))
            );
            
            assertTrue(message.isDisplayed(), "Success message should be displayed");
        });
    }

    @ParameterizedTest(name = "Browser: {0} v{1} - Cross Browser Test")
    @CsvSource({
        TestConfig.Browser.CHROME + "," + TestConfig.Version.LATEST + "," + TestConfig.Platform.WINDOWS_10,
        TestConfig.Browser.FIREFOX + "," + TestConfig.Version.LATEST + "," + TestConfig.Platform.WINDOWS_10,
        TestConfig.Browser.SAFARI + "," + TestConfig.Version.LATEST + "," + TestConfig.Platform.MACOS_MONTEREY,
        TestConfig.Browser.EDGE + "," + TestConfig.Version.LATEST + "," + TestConfig.Platform.WINDOWS_11
    })
    public void crossBrowserTest(String browser, String version, String platform) throws Exception {
        setupTest(browser, version, "Cross Browser Tests", 
                 "Cross Browser Test - " + browser + " " + version,
                 platform);
                 
        executeTest(() -> {
            driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
            
            WebElement messageInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("user-message"))
            );
            messageInput.sendKeys("Cross Browser Test");
            
            WebElement showButton = driver.findElement(By.id("showInput"));
            showButton.click();
            
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("message"))
            );
            
            assertTrue(message.isDisplayed(), "Message should be displayed");
            assertEquals("Cross Browser Test", message.getText(), "Message text should match input");
        });
    }

    @Test
    public void verifyFailingTest() throws Exception {
        setupTest("chrome", "latest", "Failing Test Demo", 
                  "Deliberately Failing Test",
                  TestConfig.Platform.WINDOWS_10);
                  
        executeTest(() -> {
            driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
            
            WebElement messageInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("user-message"))
            );
            messageInput.sendKeys("Hello World");
            
            WebElement showButton = driver.findElement(By.id("showInput"));
            showButton.click();
            
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("message"))
            );
            
            // This assertion will fail because we expect wrong text
            assertEquals("Wrong Text", message.getText(), 
                        "This test is designed to fail - actual text will be 'Hello World'");
        });
    }
} 