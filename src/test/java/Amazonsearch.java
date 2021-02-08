import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Amazonsearch {
    ChromeDriver driver;
    String url=("https://amazon.com/");
    @BeforeClass
    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\seleniumdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
       // driver.manage().timeouts().implicitlyWait(65, TimeUnit.SECONDS);
        driver.get(url);
    }


    @Test
    public void clearsearch() throws InterruptedException {
        String category="Computers";
        String product="apple watch";
        driver.findElement(By.id("searchDropdownBox")).sendKeys(category);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        List<WebElement>allproductsbeforeclear=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        System.out.println(allproductsbeforeclear.size());
        driver.findElement(By.id("twotabsearchtextbox")).clear();
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        Thread.sleep(1000);
        List<WebElement>allproductsafterclear=driver.findElements(By.xpath("//div[@class='a-column a-span4 apb-browse-searchresults-grid-lastcolumn a-span-last' or @class='a-column a-span4']"));
        System.out.println(allproductsafterclear.size());
        String productResults;
        for(WebElement products :allproductsafterclear ) {
            productResults = products.getText();
            System.out.println(productResults);
            System.out.println("------------------------------");
        }
        Assert.assertNotEquals(allproductsbeforeclear, allproductsafterclear);
    }
    @AfterClass
    public void close(){
        driver.close();
    }
}

