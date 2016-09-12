package other;

import org.openqa.selenium.WebDriver;
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

    public WebDriverWait wait;

 @BeforeMethod

    public void setUp () {

        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);




      //  driver.manage().window().maximize();


    }

 @AfterMethod

    public void closeBrowser (){

     driver.quit();
 }

}
