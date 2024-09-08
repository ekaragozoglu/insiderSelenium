package utils.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverHolder {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        WebDriverHolder.driver.set(driver);
    }
}
