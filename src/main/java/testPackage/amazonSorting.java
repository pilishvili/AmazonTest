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

    @Test //verify that first  search result in the list contains search terms
    public void amazon() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("searchDropdownBox")).sendKeys("Electronics");
        driver.findElement(By.id("twotabsearchtextbox")).clear();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("tripod");
        driver.findElement(By.className("nav-input")).click();
        Thread.sleep(7000);
        String checkResultContent = driver.findElement(By.cssSelector(".a-size-medium.a-color-null.s-inline.s-access-title.a-text-normal")).getAttribute("data-attribute");
        if (checkResultContent.contains("Tripod"))
        { System.out.println(checkResultContent + ".  Search term was: 'Tripod'"); }
        else
        { System.out.println(checkResultContent);
            Assert.fail(); }
        System.out.println("*************************************************");
    }

    @Test //Check if prices are sorted by Low to High
    public void sortPriceLowToHigh() throws InterruptedException {
        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Delectronics&field-keywords=tripod");
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.xpath("//form/select/option[@value='price-asc-rank']")).click();
        Thread.sleep(5000);
        List<WebElement> myList = driver.findElements(By.cssSelector(".a-size-base.a-color-price.s-price.a-text-bold"));
        float currentPrice;
        float previousPrice = 0;
        String priceVal;
        for (WebElement result : myList)
        {
            priceVal = result.getText();
            currentPrice = Float.valueOf(priceVal.substring(1));
            System.out.println(priceVal);
            softAssert.assertTrue(previousPrice <= currentPrice, "I expect that previous price : " + previousPrice + " is less then current price: " + currentPrice);
            previousPrice = currentPrice;
        }
        softAssert.assertAll();
    }
    @Test //Check if Prices are sorted by High to Low
    public void sortPriceHighToLow () throws InterruptedException {
        driver.get("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Delectronics&field-keywords=tripod");
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.xpath("//select[@id='sort']/option[@value='price-desc-rank']")).click();
        Thread.sleep(3000);
        List<WebElement> priceList = driver.findElements(By.cssSelector(".a-size-base.a-color-price.s-price.a-text-bold"));
        float currentPrice;
        float previousPrice =0;
        float count = 0;
        String priceVal2;
        for (WebElement result:priceList)
        {
            if (count != previousPrice){
                priceVal2 =result.getText();
                currentPrice = Float.valueOf(priceVal2.substring(1).replace(",", ""));
                System.out.println(priceVal2);
                softAssert.assertTrue(previousPrice >= currentPrice, "I Expect that previous price: " + previousPrice + " is greater or equal of current price: " + currentPrice);
                previousPrice = currentPrice;
            }
            else {
                priceVal2 =result.getText();
                currentPrice = Float.valueOf(priceVal2.substring(1).replace(",", ""));
                previousPrice = currentPrice;
            }
            count++;
        }
        softAssert.assertAll();
        System.out.println("**********************************************");
    }
}
