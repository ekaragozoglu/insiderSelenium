package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Util {
  private WebDriver driver;
  private WebDriverWait wait;

  public Util(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  public void waitForPageLoad() {
    wait.until(webDriver -> {
      JavascriptExecutor js = (JavascriptExecutor) webDriver;
      return js.executeScript("return document.readyState").equals("complete");
    });
  }

  public void waitForElementToBeVisible(By by) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public void highlightElement(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'", element);
  }

  public void clickElement(By by) {
    waitForElementToBeVisible(by);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
    highlightElement(element);
    element.click();
  }
}
