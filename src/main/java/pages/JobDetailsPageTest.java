package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JobDetailsPageTest extends BasePageTest {

  public JobDetailsPageTest(WebDriver driver) {
    super(driver);
  }

  public boolean isOnLeverPage() {
    return driver.getCurrentUrl().contains("lever.co");
  }

  public JobDetailsPageTest clickViewRole() {

    WebElement viewRoleButton = driver.findElement(By.xpath("(//a[text()='View Role'])[1]"));
    clickElement(viewRoleButton);
    return new JobDetailsPageTest(driver);
  }
}
