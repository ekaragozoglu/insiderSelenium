package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static common.CommonConstants.IMPLICIT_WAIT_IN_SECONDS;


public class HeadlessFirefoxDriverServiceImpl implements WebDriverService {
    private WebDriver driver;

    @Override
    public void spinUpDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage()
                .timeouts()
                .implicitlyWait(IMPLICIT_WAIT_IN_SECONDS);
    }

    @Override
    public void closeDriver() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
