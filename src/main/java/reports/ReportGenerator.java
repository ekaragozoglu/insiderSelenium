package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.IOException;

public class ReportGenerator {
  public static void main(String[] args) {
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("src/reports/extentReport.html");

    ExtentReports extent = new ExtentReports();
    extent.attachReporter(htmlReporter);

    ExtentTest test = extent.createTest("Scenario", "Sample description");

    test.pass("Test passed");

    try {
      test.fail("Test failed").addScreenCaptureFromPath("src/screenshots/screenshot.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    extent.flush();
  }
}
