import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class modalWindow {
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
    public void ModalWindowTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/bootstrap-modal-demo.html");
        driver.findElement(By.xpath("(//a[contains(text(),'Launch modal')])[1]")).click();
        //click on close
        Thread.sleep(2000);
        //driver.findElement(By.xpath("(//a[contains(text(),'Close')])[1]")).click();
        //click on Save Changes
        driver.findElement(By.xpath("(//a[contains(text(),'Save changes')])[1]")).click();
    }

    @Test
    public void multipleModalWindowTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/bootstrap-modal-demo.html");
        //click on inner modal window
        driver.findElement(By.xpath("(//a[contains(text(),'Launch modal')])[2]")).click();
        //click on close
        Thread.sleep(2000);
        //click on inner modal
        driver.findElement(By.xpath("(//a[contains(text(),'Launch modal')])[3]")).click();
        Thread.sleep(2000);
        //get the text
        String modal_text = driver.findElement(By.xpath("(//div[contains(text(),'This is the place where the content')])[2]")).getText();
        System.out.println(modal_text);

        WebDriverWait w1 = new WebDriverWait(driver, 15);
        WebElement close_btn = driver.findElement(By.xpath("(//a[contains(text(),'Close')])[2]"));
        WebElement close = w1.until(ExpectedConditions.elementToBeClickable(close_btn));
        close.click();
        //click on Save Changes
        //driver.findElement(By.xpath("(//a[contains(text(),'Save changes')])[2]")).click();
    }

    @Test
    public void smallModalWindowTest() throws InterruptedException {
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showSmallModal")).click();
        Thread.sleep(2000);
        String modal_text = driver.findElement(By.xpath("//div[@class='modal-content']//div[@class='modal-body']")).getText();
        System.out.println(modal_text);
        driver.findElement(By.id("closeSmallModal")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();

    }
}