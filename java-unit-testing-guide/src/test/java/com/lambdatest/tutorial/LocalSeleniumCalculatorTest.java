package com.lambdatest.tutorial;

import com.lambdatest.tutorial.config.TestConfig;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Calculator Addition Tests")
public class LocalSeleniumCalculatorTest {
    private WebDriver driver;

    @BeforeAll
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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
    @DisplayName("Test calculator addition with various inputs")
    void testAddition(String a, String b, String expected) {
        driver.get(TestConfig.APP_URL);
        WebElement firstInput = driver.findElement(By.id("sum1"));
        WebElement secondInput = driver.findElement(By.id("sum2"));
        WebElement getSumButton = driver.findElement(By.xpath("//button[text()='Get Sum']"));

        firstInput.clear();
        firstInput.sendKeys(a);
        secondInput.clear();
        secondInput.sendKeys(b);
        getSumButton.click();

        WebElement result = driver.findElement(By.id("addmessage"));
        assertEquals(expected, result.getText());
    }
} 