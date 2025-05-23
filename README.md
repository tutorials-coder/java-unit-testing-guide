# Java Unit Testing Guide with LambdaTest

This project demonstrates how to write and run unit tests using Java, JUnit 5, and Selenium WebDriver with LambdaTest's cloud platform. It includes examples of form testing, calculator functionality, and cross-browser testing.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Viewing Test Results](#viewing-test-results)
- [Test Types](#test-types)
- [Configuration](#configuration)
- [Contributing](#contributing)

## Features
- Cross-browser testing (Chrome, Firefox, Safari, Edge)
- Parallel test execution
- Parameterized tests
- Cloud-based test execution with LambdaTest
- Local test execution support
- Configurable test settings

## Prerequisites
- Java JDK 21 or higher
- Maven
- LambdaTest Account
  - Sign up at [LambdaTest](https://www.lambdatest.com/signup)
  - Get your username and access key from [LambdaTest Security Settings](https://accounts.lambdatest.com/security/username-accesskey)

## Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/java-unit-testing-guide.git
   cd java-unit-testing-guide
   ```

2. **Set up LambdaTest credentials:**
   - For Linux/Mac:
     ```bash
     export LT_USERNAME=your_username
     export LT_ACCESS_KEY=your_access_key
     ```
   - For Windows:
     ```cmd
     set LT_USERNAME=your_username
     set LT_ACCESS_KEY=your_access_key
     ```

3. **Install dependencies:**
   ```bash
   mvn clean install
   ```

## Project Structure
```
java-unit-testing-guide/
├── src/
│   ├── main/java/
│   │   └── com/lambdatest/tutorial/
│   │       └── Calculator.java
│   └── test/java/
│       └── com/lambdatest/tutorial/
│           ├── config/
│           │   └── TestConfig.java
│           ├── util/
│           │   ├── BaseTest.java
│           │   └── WebDriverUtil.java
│           ├── CloudCalculatorTest.java
│           ├── LocalSeleniumCalculatorTest.java
│           └── CalculatorTest.java
└── pom.xml
```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=CloudCalculatorTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=CloudCalculatorTest#testWithDifferentBrowsers
```

## Viewing Test Results

### LambdaTest Dashboard
1. Log in to [LambdaTest Automation Builds](https://automation.lambdatest.com/build?pageType=build)
2. Find your build using the format: "Calculator Tests - YYYY-MM-DD HH:mm:ss"

### Local Test Reports
Test reports are generated in:
```
target/surefire-reports/
```

## Test Types

### 1. Local Calculator Tests (`CalculatorTest.java`)
- Pure unit tests for calculator logic
- No browser dependency
- Fast execution

### 2. Local Selenium Tests (`LocalSeleniumCalculatorTest.java`)
- Selenium tests running on local machine
- Chrome browser only
- Good for quick local testing

### 3. Cloud Calculator Tests (`CloudCalculatorTest.java`)
- Cross-browser testing on LambdaTest platform
- Tests calculator functionality across different browsers
- Parallel execution support
- Build naming format: "Calculator Tests - {timestamp}"

## Configuration

### Test Configuration (`TestConfig.java`)
The `TestConfig` class centralizes all test configuration settings:

- **LambdaTest Credentials**: Environment variables for username and access key
- **Application URL**: The target website for testing
- **Build Configuration**: Format for build names and timestamps
- **Browser Settings**: Supported browsers (Chrome, Firefox, Safari, Edge)
- **Version Settings**: Browser versions (latest, specific versions)
- **Platform Settings**: Operating systems (Windows 10, macOS Monterey)
- **Test Status**: Constants for test results (passed, failed)

### Parallel Execution (`junit-platform.properties`)
```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.mode.classes.default=concurrent
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=5
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`