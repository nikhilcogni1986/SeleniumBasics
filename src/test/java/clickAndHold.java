import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class clickAndHold {
    WebDriver driver;

    @BeforeTest
    public void setDriver() {
        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\chromedriver.exe";

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void clickAndHoldTest() throws InterruptedException {
        driver.get("https://dhtmlx.com/docs/products/dhtmlxTree/");
        driver.findElement(By.xpath("//label[@class='dhx_demo-switch__label dhx_label' and @for='checkbox']")).click();
        WebElement source = driver.findElement(By.xpath("//span[contains(text(),'Learning DHTMLX Suite UI')]"));
        WebElement destination = driver.findElement(By.xpath("//ul[contains(@class,'dhx_widget dhx_list')]//li[contains(text(),'Ajax in Practice')]"));
        Thread.sleep(2000);
        Actions act = new Actions(driver);
        act.clickAndHold(source).pause(2000).moveToElement(destination).release().build().perform();
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}