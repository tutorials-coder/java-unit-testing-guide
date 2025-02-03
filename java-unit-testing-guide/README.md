# **Java Unit Testing with LambdaTest**

This project demonstrates Java unit testing with **Selenium WebDriver** using **LambdaTest's cloud platform**. It includes examples of form testing, checkbox interactions, and e-commerce functionality across different browsers.

---

## **Prerequisites**

- **Java JDK 8** or higher
- **Maven**
- **LambdaTest Account** *(for running Selenium tests)*

---

## **Setup**

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd java-unit-testing
   ```

2. **Set up LambdaTest credentials as environment variables:**

    - **For Linux/Mac:**
      ```bash
      export LT_USERNAME=your_username
      export LT_ACCESS_KEY=your_access_key
      ```

    - **For Windows:**
      ```cmd
      set LT_USERNAME=your_username
      set LT_ACCESS_KEY=your_access_key
      ```

3. **Install dependencies:**

   ```bash
   mvn clean install
   ```

---

## **Running Tests**

- **Run all tests:**

  ```bash
  mvn test
  ```

- **Run a specific test:**

  ```bash
  mvn test -Dtest=LambdaTestDemo#testName
  ```

---

## **Test Scenarios**

1. **Form Input Testing**
    - Validates simple form input and display
    - Runs across multiple browsers

2. **Checkbox Testing**
    - Verifies checkbox functionality
    - Tests success message display

3. **E-commerce Testing**
    - Tests product search functionality
    - Validates search results

![img.png](unit_tests.png)
![img.png](lambdatest_automation.png)
---

## **Configuration**

The tests run on the following configurations:

- **Browsers:**
    - Chrome (latest and 114.0)
    - Firefox (latest and 113.0)

- **Platform:**
    - Windows 10

---

## **Contributing**

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Create a Pull Request

---

## **License**

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---
