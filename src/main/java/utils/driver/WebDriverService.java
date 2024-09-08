package utils.driver;

import org.openqa.selenium.WebDriver;

public interface WebDriverService {
    void spinUpDriver();

    void closeDriver();

    WebDriver getDriver();
}
