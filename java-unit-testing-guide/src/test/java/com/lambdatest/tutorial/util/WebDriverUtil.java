package com.lambdatest.tutorial.util;

import java.net.URI;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.lambdatest.tutorial.config.TestConfig;

public class WebDriverUtil {
    
    public static WebDriver createDriver(String browser, String version, String buildName,
                                       String testName, String platform) throws Exception {
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", buildName);
        ltOptions.put("name", testName);
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", platform);
        ltOptions.put("browserVersion", version);

        return switch (browser.toLowerCase()) {
            case TestConfig.Browser.FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(TestConfig.GRID_URL).toURL(), firefoxOptions);
            }
            case TestConfig.Browser.SAFARI -> {
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(TestConfig.GRID_URL).toURL(), safariOptions);
            }
            case TestConfig.Browser.EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(TestConfig.GRID_URL).toURL(), edgeOptions);
            }
            default -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("LT:Options", ltOptions);
                yield new RemoteWebDriver(URI.create(TestConfig.GRID_URL).toURL(), chromeOptions);
            }
        };
    }

    public static void markStatus(WebDriver driver, String status) {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        }
    }
} 