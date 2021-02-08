import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchFunctionality {
    ChromeDriver driver;
    String url=("https://www.bbc.co.uk/search");
    @BeforeTest
    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\seleniumdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(65, TimeUnit.SECONDS);

    }
    @Test(priority = 100)
    public void validSearch() throws InterruptedException {
        driver.get(url);
        String validsearch="vaccine";
        WebElement searchlink=driver.findElement(By.xpath("//a[@href='/search']"));
        searchlink.click();
        WebElement searchbox=driver.findElement(By.xpath("//*[@id='search-input']"));
        searchbox.sendKeys(validsearch);
        driver.findElement(By.xpath("//*[@data-testid='test-search-submit']")).click();
        Thread.sleep(2000);

        WebElement element=driver.findElement(By.xpath("//body"));
        String searchresults=element.getText();
        if(!searchresults.contains("Sorry, there are no results for")){
            List<WebElement> allelements=driver.findElements(By.xpath("//*[@id='main-content']/div/div[3]/div/div/ul/li/descendant::a/span"));
            for (WebElement eachelemet:allelements){
                if(eachelemet.getText().toLowerCase().contains("vaccine")) {
                    System.out.println("text:" + eachelemet.getText());
                    System.out.println("------");
                    Assert.assertTrue(true);
                }
                else
                    Assert.assertTrue(false);
            }
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);

    }
    @Test(priority = 200)
    public void inValidSearch() throws InterruptedException {
        driver.get(url);
        String searchstring="zyxgdef";
        WebElement searchlink=driver.findElement(By.xpath("//a[@href='/search']"));
        searchlink.click();
        WebElement searchbox=driver.findElement(By.xpath("//*[@id='search-input']"));
        searchbox.sendKeys(searchstring);
        driver.findElement(By.xpath("//*[@data-testid='test-search-submit']")).click();
        Thread.sleep(2000);
        WebElement element=driver.findElement(By.xpath("//body"));
        String searchresults=element.getText();
        if(searchresults.contains("Sorry, there are no results for"))
            Assert.assertTrue(true);
        else
            Assert.assertTrue(false);

    }
    @AfterClass
    public void close(){
        driver.quit();
    }
}
