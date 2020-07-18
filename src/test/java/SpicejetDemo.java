import Utility.waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpicejetDemo {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void SpiceJetTest() {
        driver.get("https://www.spicejet.com/default.aspx");
        System.out.println(driver.getTitle());

        //click on drop down to select the currency
        WebElement currency = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select currency_list = new Select(currency);
        currency_list.selectByVisibleText("AED");

        //select the source and destination
//        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
//        driver.findElement(By.xpath("//a[@text='Bengaluru (BLR)']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("(//a[@text='Chennai (MAA)'])[2]")).click();

        //using explicit wait
        By source = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
        By source_City = By.xpath("//a[@text='Bengaluru (BLR)']");
        By destination = By.xpath("(//a[@text='Chennai (MAA)'])[2]");

        waits.click(driver, source);
        waits.click(driver, source_City);
        waits.click(driver, destination);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}