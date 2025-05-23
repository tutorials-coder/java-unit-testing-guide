package com.lambdatest.tutorial;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lambdatest.tutorial.config.TestConfig;
import com.lambdatest.tutorial.util.BaseTest;

@DisplayName("Cloud Calculator Tests")
class CloudCalculatorTest extends BaseTest {
    private static final String BUILD_NAME = String.format(TestConfig.Build.NAME_FORMAT, 
        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(TestConfig.Build.TIMESTAMP_FORMAT)));

    private static Stream<Arguments> browserConfigurations() {
        return Stream.of(
            Arguments.of(TestConfig.Browser.CHROME, TestConfig.Version.LATEST, TestConfig.Platform.WINDOWS_10),
            Arguments.of(TestConfig.Browser.FIREFOX, TestConfig.Version.LATEST, TestConfig.Platform.WINDOWS_10),
            Arguments.of(TestConfig.Browser.EDGE, TestConfig.Version.LATEST, TestConfig.Platform.WINDOWS_10),
            Arguments.of(TestConfig.Browser.SAFARI, TestConfig.Version.LATEST, TestConfig.Platform.MACOS_MONTEREY)
        );
    }

    @ParameterizedTest(name = "Browser: {0}, Version: {1}, Platform: {2}")
    @MethodSource("browserConfigurations")
    @DisplayName("Addition Test - Browser: {0}, Version: {1}, Platform: {2}")
    void testWithDifferentBrowsers(String browser, String version, String platform) throws Exception {
        String testName = String.format("Addition Test - Browser: %s, Version: %s, Platform: %s", browser, version, platform);
        setupTest(browser, version, BUILD_NAME, testName, platform);
        executeTest(() -> {
            WebElement firstInput = driver.findElement(By.id("sum1"));
            WebElement secondInput = driver.findElement(By.id("sum2"));
            WebElement getSumButton = driver.findElement(By.xpath("//button[contains(text(), 'Get Sum')]"));

            firstInput.clear();
            firstInput.sendKeys("5");
            secondInput.clear();
            secondInput.sendKeys("7");
            wait.until(ExpectedConditions.elementToBeClickable(getSumButton));
            getSumButton.click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("addmessage"), "12"));

            WebElement result = driver.findElement(By.id("addmessage"));
            assertEquals("12", result.getText());
        });
    }

    @ParameterizedTest(name = "Test Case {index}: {0} + {1} = {2}")
    @CsvSource({
        "5, 7, 12",
        "1, 1, 2",
        "-2, 3, 1",
        "100, 200, 300",
        "1000000, 2000000, 3000000",
        "999999, 1, 1000000",
        "abc, def, Entered value is not a number",
        "xyz, 123, Entered value is not a number",
        "2147483647, 1, 2147483648",
        "'', '', Entered value is not a number",
        "' ', ' ', Entered value is not a number"
    })
    @DisplayName("Addition Test - Inputs: {0} + {1} = {2}")
    void testAddition(String a, String b, String expected) throws Exception {
        String testName = String.format("Addition Test - %s + %s = %s", a, b, expected);
        setupTest(TestConfig.Browser.CHROME, TestConfig.Version.LATEST, BUILD_NAME, testName, TestConfig.Platform.WINDOWS_10);
        executeTest(() -> {
            WebElement firstInput = driver.findElement(By.id("sum1"));
            WebElement secondInput = driver.findElement(By.id("sum2"));
            WebElement getSumButton = driver.findElement(By.xpath("//button[contains(text(), 'Get Sum')]"));

            firstInput.clear();
            firstInput.sendKeys(a);
            secondInput.clear();
            secondInput.sendKeys(b);
            wait.until(ExpectedConditions.elementToBeClickable(getSumButton));
            getSumButton.click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("addmessage"), expected));

            WebElement result = driver.findElement(By.id("addmessage"));
            assertEquals(expected, result.getText());
        });
    }
} 