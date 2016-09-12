package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import other.settings;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by davitpilishvili on 9/10/16.
 */
public class amazonSorting extends settings {

    String continueFromCurrentUrl;
    @Test
    public void amazon () throws InterruptedException {

        driver.get("https://www.amazon.com/");

        driver.findElement(By.id("searchDropdownBox")).sendKeys("Electronics");

        driver.findElement(By.id("twotabsearchtextbox")).clear();

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("tripod");

        driver.findElement(By.className("nav-input")).click();

        Thread.sleep(10000);

        //verify that first  search result in the list contains search term's
        String checkResultContent = driver.findElement(By.xpath("//ul/li[@id='result_0']/div/div/div/div[2]/div[2]/a/h2")).getAttribute("data-attribute");

       if (checkResultContent.contains("Tripod")){
            System.out.println(checkResultContent + ".  Search term was: 'Tripod'");
        }

        else {
           System.out.println(checkResultContent);
            Assert.fail();
        }

        continueFromCurrentUrl = driver.getCurrentUrl();
        System.out.println(continueFromCurrentUrl);

    }
    @Test
    public void sortPriceLowToHigh () throws InterruptedException {

        driver.get("https://www.amazon.com/s/ref=nb_sb_noss/166-9809752-8807531?url=search-alias%3Delectronics&field-keywords=tripod");

        driver.findElement(By.id("sort")).click();

        driver.findElement(By.xpath("//form/select/option[@value='price-asc-rank']")).click();

       // String lowestPrice = driver.findElement(By.tagName("//li[@id='result_0']/div/div[4]/div/a[@class='a-link-normal a-text-normal']/span")).getText();
       // Thread.sleep(10000);


        String results[] = new String[23];

        results[0]= "li[id='result_0']>div>div:nth-child(4)>div>a>span";
        results[1]= "li[id='result_1']>div>div:nth-child(4)>div>a>span";
        results[2]= "li[id='result_2']>div>div:nth-child(4)>div>a>span";
        results[3]= "li[id='result_3']>div>div:nth-child(4)>div>a>span";
        results[4]= "li[id='result_4']>div>div:nth-child(4)>div>a>span";
        results[5]= "li[id='result_5']>div>div:nth-child(4)>div>a>span";
        results[6]= "li[id='result_6']>div>div:nth-child(4)>div>a>span";
        results[7]= "li[id='result_7']>div>div:nth-child(4)>div>a>span";
        results[8]= "li[id='result_8']>div>div:nth-child(4)>div>a>span";
        results[9]= "li[id='result_9']>div>div:nth-child(4)>div>a>span";
        results[10]= "li[id='result_10']>div>div:nth-child(4)>div>a>span";
        results[11]= "li[id='result_11']>div>div:nth-child(4)>div>a>span";
        results[12]= "li[id='result_12']>div>div:nth-child(4)>div>a>span";
        results[13]= "li[id='result_13']>div>div:nth-child(4)>div>a>span";
        results[14]= "li[id='result_14']>div>div:nth-child(4)>div>a>span";
        results[15]= "li[id='result_16']>div>div:nth-child(4)>div>a>span";
        results[16]= "li[id='result_17']>div>div:nth-child(4)>div>a>span";
        results[17]= "li[id='result_18']>div>div:nth-child(4)>div>a>span";
        results[18]= "li[id='result_19']>div>div:nth-child(4)>div>a>span";
        results[19]= "li[id='result_20']>div>div:nth-child(4)>div>a>span";
        results[20]= "li[id='result_21']>div>div:nth-child(4)>div>a>span";
        results[21]= "li[id='result_22']>div>div:nth-child(4)>div>a>span";
        results[22]= "li[id='result_23']>div>div:nth-child(4)>div>a>span";

      //  WebElement price;

        for (int i = 0; i < results.length; i++){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(results[i])));
        String lowerPrice = driver.findElement(By.cssSelector(results[i])).getText();

            double lowPrice = Double.parseDouble(lowerPrice.replace("$", ""));

           // System.out.println(lowerPrice);
           System.out.println(lowPrice);
           // Thread.sleep(3000);



        }



        //System.out.println("This is a lowest Price: " + lowerPrice);


    }
}
