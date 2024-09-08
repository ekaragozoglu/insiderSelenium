package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
  private static WebDriver driver;

  public static WebDriver initializeDriver(String browser) {
    if ("chrome".equalsIgnoreCase(browser)) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--incognito");
      driver = new ChromeDriver(options);
    } else if ("firefox".equalsIgnoreCase(browser)) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments("-private");
      driver = new FirefoxDriver(options);
    }
    driver.manage().window().maximize();
    return driver;
  }

  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
    }
  }
}
