package other;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver.Timeouts;

import java.util.concurrent.TimeUnit;

/**
 * Created by davitpilishvili on 9/10/16.
 */
public class settings {

    public WebDriver driver;

 @BeforeMethod
    public void setUp () {
      driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
 @AfterMethod
    public void closeBrowser (){
     driver.quit();
 }
}
