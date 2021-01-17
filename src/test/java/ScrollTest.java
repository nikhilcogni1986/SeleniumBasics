import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScrollTest {
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
    public void scrollUsingJS() throws InterruptedException {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,500)");
        Thread.sleep(2000);

        js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=500");
        List<WebElement> columnFour = driver.findElements(By.xpath("//div[@class='tableFixHead']//tbody//tr//td[4]"));
        int sum = 0;
        for (int i = 0; i < columnFour.size(); i++) {
            int column_value = Integer.parseInt(columnFour.get(i).getText());
            sum = sum + column_value;
        }
        System.out.println("Total Sum of 4th column is: " + sum);

        //get the text of final count value
        int total = Integer.parseInt(driver.findElement(By.cssSelector("div[class='totalAmount']")).getText().split(":")[1].trim());
        Assert.assertEquals(total, sum);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}