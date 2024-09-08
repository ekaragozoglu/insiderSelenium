# Insider QA Website Test Automation

This project is an automated test suite for the Insider QA website. The project is designed to verify the functionality and content of various web pages on the Insider website, particularly the QA-related sections.

## Project Overview

- **Project Type**: Maven Project
- **Programming Language**: Java
- **Libraries Used**: Selenium, JUnit, ExtentReports
- **Design Pattern**: Page Object Model (POM)
- **Principles**: Object-Oriented Programming (OOP)

## Project Structure

This project follows the Page Object Model (POM) design pattern, which helps to make the test cases more readable and maintainable. Each page of the website is represented by a corresponding Java class, encapsulating its web elements and the actions that can be performed on them.

### Key Components

- **Pages**:
    - `HomePageTest`: Represents the main page of the Insider website.
    - `CareerPageTest`: Represents the career page, where job listings can be filtered.
    - `JobDetailsPageTest`: Represents the details page for a selected job.

- **Tests**:
    - Test cases verify the proper functionality of different features of the website, including navigation, filtering QA jobs, and ensuring content is displayed correctly.

- **Utilities**:
    - Includes methods to handle common actions like waiting for elements, page load synchronization, and interaction with cookies.

## Technologies Used

- **Selenium WebDriver**: Used for browser automation to interact with the Insider website.
- **JUnit**: Framework for writing and running tests.
- **Maven**: For project management and dependency handling.

## How to Run the Project

1. **Clone the Repository**:
- git clone git@github.com:ekaragozoglu/insiderSelenium.git

2. **Check POM.xml**:
   Make sure you have Maven installed. Run the following command to install all dependencies:

3. **Run the Tests**:
    ```chmod +x run_tests.sh
    ./run_tests.sh
    ```
4. **Reporting**:
   Test reports can be generated using Maven and can be viewed after the tests are executed.

## Project Features
- **Cross-Browser Testing**: The project supports both Chrome and Firefox browsers.
- **Dynamic Element Handling**: The project uses various dynamic wait methods (like `WebDriverWait` and JavaScript execution) to ensure elements are interacted with at the right time.
- **Cookie Management**: The project handles cookie consent using a method to automatically accept cookies on the website.
