import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserLoggedIn {
    ChromeDriver driver;
    String url=("https://account.bbc.com/");
    @BeforeTest
    public void invokeBrowser(){
        System.setProperty("webdriver.chrome.driver", "D:\\seleniumdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(65, TimeUnit.SECONDS);


    }

    @Test
    public void invalidLogin() throws InterruptedException {
        driver.get(url);
        WebElement username= driver.findElement(By.id("user-identifier-input"));
        WebElement password= driver.findElement(By.id("password-input"));
        WebElement login= driver.findElement(By.id("submit-button"));

        username.sendKeys("lahariullaudi@gmail.com");
        password.sendKeys("bbc@BBC@");
        login.click();
        String errorMsg = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/form/div[1]/div[2]/p/span/span")).getText();
        System.out.println(errorMsg);
    }
    @Test(priority = 100)
    public void validLogIn(){
        driver.get(url);
        WebElement username= driver.findElement(By.id("user-identifier-input"));
        WebElement password= driver.findElement(By.id("password-input"));
        WebElement login= driver.findElement(By.id("submit-button"));
        username.sendKeys("laharimullapudi@gmail.com");
        password.sendKeys("bbc@BBC@");
        login.click();
        System.out.println(driver.getTitle());
    }

    @AfterTest
    public void close(){
        driver.quit();
    }
}
