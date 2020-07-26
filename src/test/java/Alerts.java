import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Alerts {
    WebDriver driver;

    @BeforeTest
    public void setDriver() {
        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\chromedriver.exe";

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void javaScriptAlertTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        driver.findElement(By.xpath("(//button[contains(text(),'Click me!')])[1]")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        // 2nd pop up with Ok and cancel
        driver.findElement(By.xpath("(//button[contains(text(),'Click me!')])[2]")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        // 3rd pop up - confirmation pop up
        driver.findElement(By.xpath("//button[contains(text(),'Click for Prompt Box')]")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("Hello");
        alert.accept();
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}
