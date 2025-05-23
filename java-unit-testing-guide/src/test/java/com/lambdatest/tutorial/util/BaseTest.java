package com.lambdatest.tutorial.util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.tutorial.config.TestConfig;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    protected void setupTest(String browser, String version, String buildName, 
                           String testName, String platform) throws Exception {
        driver = WebDriverUtil.createDriver(browser, version, buildName, testName, platform);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    protected void executeTest(TestRunnable testLogic) throws Exception {
        try {
            driver.get(TestConfig.APP_URL);
            testLogic.run();
            WebDriverUtil.markStatus(driver, TestConfig.Status.PASSED);
        } catch (Exception e) {
            WebDriverUtil.markStatus(driver, TestConfig.Status.FAILED);
            throw e;
        } finally {
            tearDown();
        }
    }

    @FunctionalInterface
    protected interface TestRunnable {
        void run() throws Exception;
    }
} 