package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageTest extends BasePageTest {

  private By companyButton = By.xpath("(//a[@id='navbarDropdownMenuLink'])[5]");
  private By careersLink = By.xpath("//a[@class='dropdown-sub'][text()='Careers']");

  public HomePageTest(WebDriver driver) {
    super(driver);
  }

  public void goToCareersPage() {
    WebElement dropdown = driver.findElement(companyButton);
    clickElement(dropdown);
    System.out.println("Clicking on Company tab");
    WebElement careers = driver.findElement(careersLink);
    clickElement(careers);
    System.out.println("Clicking on Careers link");
  }
}
