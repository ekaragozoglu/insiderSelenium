package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

public class CareerPageTest extends BasePageTest {

  private By seeAllQAJobsButton = By.xpath("//a[text()='See all QA jobs']");
  private By locationFilter = By.cssSelector("#select2-filter-by-location-container");
  private By departmentFilter = By.cssSelector("#select2-filter-by-department-container");
  private By selectIstanbul = By.cssSelector("li.select2-results__option[data-select2-id*='Istanbul']");
  private By selectQa = By.cssSelector("li.select2-results__option[data-select2-id*='Quality Assurance']");

  public CareerPageTest(WebDriver driver) {
    super(driver);
  }

  @Test
  public void filterQAJobsInIstanbul() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(22));

    WebElement seeAllQAJobs = driver.findElement(seeAllQAJobsButton);
    waitForPageLoad();
    clickElement(seeAllQAJobs);
    System.out.println("Clicking on 'See all QA jobs' button");

    waitForPageLoad();
    WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
    clickElement(locationDropdown);
    System.out.println("Clicking on location dropdown");

    waitForPageLoad();
    WebElement istanbulOption = wait.until(ExpectedConditions.elementToBeClickable(selectIstanbul));
    js.executeScript("arguments[0].scrollIntoView(true);", istanbulOption);
    clickElement(istanbulOption);
    System.out.println("Clicking on Istanbul option");

    waitForPageLoad();
    WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(departmentFilter));
    js.executeScript("arguments[0].scrollIntoView(true);", departmentDropdown);
    clickElement(departmentDropdown);
    System.out.println("Clicking on department dropdown");
    System.out.println("Clicking on Quality Assurance option");

    waitForPageLoad();
    WebElement qualityAssuranceOption = wait.until(ExpectedConditions.elementToBeClickable(selectQa));
    js.executeScript("window.scrollBy(0, 1000);");
    clickElement(qualityAssuranceOption);
    System.out.println("Clicking on Quality Assurance option");
  }

  @Test
  public void setSeeAllQAJobsButton() {
    WebElement jobList = driver.findElement(By.cssSelector("#jobs-list"));
    assertTrue(jobList.isDisplayed());

    WebElement firstJob = driver.findElement(By.cssSelector("#jobs-list"));
    highlightElement(firstJob);

    assertTrue(firstJob.getText().contains("Quality Assurance"));
    assertTrue(firstJob.getText().contains("Istanbul, Turkey"));
  }
}
