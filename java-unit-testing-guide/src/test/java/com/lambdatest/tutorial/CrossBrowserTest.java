package com.lambdatest.tutorial;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrossBrowserTest {
    
    private static final String USERNAME = System.getenv("LT_USERNAME");
    private static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    private static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";
    
    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox", "safari"})
    public void testSimpleForm(String browser) throws Exception {
        WebDriver driver = null;
        try {
            driver = setupDriver(browser);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            
            // Navigate to Simple Form Demo
            driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
            
            // Find and fill the single input field
            WebElement messageInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#user-message")
                )
            );
            messageInput.sendKeys("Test from " + browser);
            
            // Click the Get Checked Value button
            WebElement showMessageButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#showInput")
                )
            );
            showMessageButton.click();
            
            // Verify the message
            WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#message")
                )
            );
            assertEquals("Test from " + browser, message.getText(), 
                        "Message should match input");
            
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
    
    private WebDriver setupDriver(String browser) throws Exception {
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Cross Browser Test");
        ltOptions.put("name", "Multi Browser Test - " + browser);
        ltOptions.put("w3c", true);
        
        return switch(browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName("Windows 10");
                firefoxOptions.setBrowserVersion("latest");
                firefoxOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(GRID_URL).toURL(), firefoxOptions);
            }
            case "safari" -> {
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPlatformName("MacOS Monterey");
                safariOptions.setBrowserVersion("latest");
                safariOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(GRID_URL).toURL(), safariOptions);
            }
            default -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName("Windows 10");
                chromeOptions.setBrowserVersion("latest");
                chromeOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(GRID_URL).toURL(), chromeOptions);
            }
        };
    }
} 