package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.CareerPageTest;
import pages.HomePageTest;
import pages.JobDetailsPageTest;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.junit.Assert.assertTrue;

public class TestAutomation {

  private WebDriver driver;
  private HomePageTest homePageTest;
  private CareerPageTest careerPageTest;
  private JobDetailsPageTest jobDetailsPageTest;
  private String browser = "chrome"; // select "chrome" or "firefox"

  private ExtentReports extent;
  private ExtentTest test;

  @Before
  public void setUp() {
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("src/reports/extent-report.html");
    sparkReporter.config().setTheme(Theme.STANDARD);
    sparkReporter.config().setDocumentTitle("Test Automation Report");
    sparkReporter.config().setReportName("Test Report");

    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);

    if ("chrome".equalsIgnoreCase(browser)) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--incognito");
      options.addArguments("--no-sandbox");
      //options.addArguments("--headless");
      options.addArguments("disable-infobars");
      options.addArguments("--disable-extensions");
      options.addArguments("--disable-gpu");
      options.addArguments("--disable-dev-shm-usage");
      driver = new ChromeDriver(options);
    } else if ("firefox".equalsIgnoreCase(browser)) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments("-private");
      //options.addArguments("--headless");
      options.addArguments("--disable-gpu");
      driver = new FirefoxDriver(options);
    }
    driver.manage().window().maximize();
    homePageTest = new HomePageTest(driver);
    careerPageTest = new CareerPageTest(driver);

    File screenshotsDir = new File("src/screenshots");
    if (!screenshotsDir.exists()) {
      screenshotsDir.mkdir();
    }
    homePageTest.acceptCookies();
  }

  private void captureScreenshot(String screenshotName) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String screenshotPath = "src/screenshots/" + screenshotName + ".png";
    try {
      FileUtils.copyFile(screenshot, new File(screenshotPath));
      System.out.println("Screenshot saved at: " + screenshotPath);
      test.fail("Test failed").addScreenCaptureFromPath(screenshotPath);
    } catch (IOException e) {
      System.err.println("Failed to save screenshot: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  public void testMainPage() {
    test = extent.createTest("testMainPage", "Verify the main page title contains 'Insider'");
    driver.get("https://useinsider.com/");
    homePageTest.waitForPageLoad();
    String title = driver.getTitle();
    try {
      assertTrue(title.contains("Insider"));
      test.pass("Main page title contains 'Insider'");
    } catch (AssertionError e) {
      captureScreenshot("testMainPage");
      throw e;
    } catch (Exception e) {
      captureScreenshot("testMainPage");
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testCareerPage() {
    test = extent.createTest("testCareerPage", "Verify the career page elements");
    driver.get("https://useinsider.com/");
    homePageTest.goToCareersPage();
    try {
      assertTrue(driver.getCurrentUrl().contains("careers"));
      assertTrue(driver.findElement(By.cssSelector("#career-find-our-calling")).isDisplayed());
      assertTrue(driver.findElement(By.cssSelector("#career-our-location")).isDisplayed());
      assertTrue(driver.findElement(By.xpath("//div[@data-id='87842ec']")).isDisplayed());
      test.pass("Career page elements are displayed correctly");
    } catch (AssertionError e) {
      captureScreenshot("testCareerPage");
      throw e;
    } catch (Exception e) {
      captureScreenshot("testCareerPage");
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFilterQAJobsInIstanbul() {
    test = extent.createTest("testFilterQAJobsInIstanbul", "Filter QA jobs in Istanbul");
    driver.get("https://useinsider.com/careers/quality-assurance/");
    try {
      careerPageTest.filterQAJobsInIstanbul();
      test.pass("Filtered QA jobs in Istanbul");
    } catch (AssertionError e) {
      captureScreenshot("testFilterQAJobsInIstanbul");
      throw e;
    } catch (Exception e) {
      captureScreenshot("testFilterQAJobsInIstanbul");
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testJobDetails() {
    test = extent.createTest("testJobDetails", "Verify job details for QA jobs in Istanbul");
    driver.get("https://useinsider.com/careers/quality-assurance/");
    try {
      careerPageTest.filterQAJobsInIstanbul();
      jobDetailsPageTest = jobDetailsPageTest.clickViewRole();
      careerPageTest.setSeeAllQAJobsButton();
      test.pass("Job details for QA jobs in Istanbul are correct");
    } catch (AssertionError e) {
      captureScreenshot("testJobDetails");
      throw e;
    } catch (Exception e) {
      captureScreenshot("testJobDetails");
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testViewRoleButton() {
    test = extent.createTest("testViewRoleButton", "Verify the view role button functionality");
    driver.get("https://useinsider.com/careers/quality-assurance/");
    try {
      careerPageTest.filterQAJobsInIstanbul();
      jobDetailsPageTest = jobDetailsPageTest.clickViewRole();

      assertTrue(jobDetailsPageTest.isOnLeverPage());
      test.pass("View role button works correctly");
    } catch (AssertionError e) {
      captureScreenshot("testViewRoleButton");
      throw e;
    } catch (Exception e) {
      captureScreenshot("testViewRoleButton");
      throw new RuntimeException(e);
    }
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
    extent.flush();
  }
}
