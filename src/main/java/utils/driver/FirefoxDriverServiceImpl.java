package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static common.CommonConstants.IMPLICIT_WAIT_IN_SECONDS;

public class FirefoxDriverServiceImpl implements WebDriverService {
    private WebDriver driver;

    @Override
    public void spinUpDriver() {
        driver = WebDriverManager.firefoxdriver().create();
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
