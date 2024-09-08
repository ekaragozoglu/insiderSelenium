package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

  private static final Duration TIMEOUT = Duration.ofSeconds(20);

  public static void waitForPageLoad(WebDriver driver) {
    new WebDriverWait(driver, TIMEOUT).until(webDriver ->
        ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
    );
  }

  public static void waitForElementToBeVisible(WebDriver driver, By locator) {
    new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public static void clickElement(WebDriver driver, By locator) {
    waitForElementToBeVisible(driver, locator);
    WebElement element = new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }
}
