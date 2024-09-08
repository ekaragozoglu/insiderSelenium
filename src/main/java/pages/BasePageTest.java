package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageTest {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePageTest(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  public void waitForPageLoad() {
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    wait.until(webDriver -> {
      JavascriptExecutor js = (JavascriptExecutor) webDriver;
      return js.executeScript("return document.readyState").equals("complete");
    });
  }

  protected void waitForElementToBeVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void highlightElement(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'", element);
  }

  protected void clickElement(WebElement element) {
    waitForElementToBeVisible(element);
    highlightElement(element);
    element.click();
  }

  public void acceptCookies() {
    try {
      driver.switchTo().frame("iframeID");

      WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-law-info-bar")));

      if (acceptCookiesButton != null) {
        clickElement(acceptCookiesButton);
        System.out.println("Cookies accepted");
      } else {
        System.out.println("No cookies button found or already accepted");
      }
      driver.switchTo().defaultContent();
    } catch (Exception e) {
      System.out.println("No cookies button found or already accepted");
    }
  }
}
