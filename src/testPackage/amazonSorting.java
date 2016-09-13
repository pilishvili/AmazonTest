package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import other.settings;

import java.util.List;

/**
 * Created by davitpilishvili on 9/10/16.
 */
public class amazonSorting extends settings {

    String continueFromCurrentUrl;

    private SoftAssert softAssert = new SoftAssert();


    @Test
    public void amazon() throws InterruptedException {

        driver.get("https://www.amazon.com/");

        driver.findElement(By.id("searchDropdownBox")).sendKeys("Electronics");

        driver.findElement(By.id("twotabsearchtextbox")).clear();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("tripod");

        driver.findElement(By.className("nav-input")).click();

        Thread.sleep(10000);

        //verify that first  search result in the list contains search term's
        String checkResultContent = driver.findElement(By.xpath("//ul/li[@id='result_0']/div/div/div/div[2]/div[2]/a/h2")).getAttribute("data-attribute");

        if (checkResultContent.contains("Tripod")) {
            System.out.println(checkResultContent + ".  Search term was: 'Tripod'");
        } else {
            System.out.println(checkResultContent);
            Assert.fail();
        }

        continueFromCurrentUrl = driver.getCurrentUrl();
        System.out.println(continueFromCurrentUrl);

    }

    @Test
    public void sortPriceLowToHigh() throws InterruptedException {

        driver.get("https://www.amazon.com/s/ref=nb_sb_noss/166-9809752-8807531?url=search-alias%3Delectronics&field-keywords=tripod");

        driver.findElement(By.id("sort")).click();

        driver.findElement(By.xpath("//form/select/option[@value='price-asc-rank']")).click();

        //String lowestPrice = driver.findElement(By.tagName("//li[@id='result_0']/div/div[4]/div/a[@class='a-link-normal a-text-normal']/span")).getText();
        Thread.sleep(5000);

        List<WebElement> myList = driver.findElements(By.cssSelector(".a-size-base.a-color-price.s-price.a-text-bold"));

        float currentPrice;
        float previousPrice = 0;
        String priceVal;

        for (WebElement result : myList) {
            priceVal = result.getText();
            currentPrice = Float.valueOf(priceVal.substring(1));
            System.out.println(priceVal);
            softAssert.assertTrue(previousPrice <= currentPrice, "I expect that previous price : " + previousPrice + " is less then current price: " + currentPrice);
            previousPrice = currentPrice;
        }
        softAssert.assertAll();
    }
}
