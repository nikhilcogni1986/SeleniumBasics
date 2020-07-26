import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class hiddenElements {
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
    public void hiddenElementTest() throws InterruptedException {
        driver.get("https://learn.letskodeit.com/p/practice");

        // execute this set of code and you will see element not visible exception
        // Clicking on the Hide button
        driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
        // driver.findElement(By.xpath("//input[@id='displayed-text']")).sendKeys("text345");

        // execute this code to handle above scenario
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Identifying the element using ID attribute and Entering the value in the text box
        js.executeScript("document.getElementById('displayed-text').value='text123'");
        Thread.sleep(5000);
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}

/*
WHEN XPATH IS DEFINED
WebElement ele = driver.findElement(By.xpath(“xpath”)));
((JavascriptExecutor)driver).executeScript(“arguments[0].click();”,ele);
 */

/*
SCROLL WEB PAGE UNTIL IT IS VISIBLE
WebElement ele = driver.findElement(By.xpath(“xpath”)));
((JavascriptExecutor)driver).executeScript(“arguments[0].scrollIntoView(true);”,ele);
 */

/*
MAKE HIDDEN ELEMENTS VISIBLE AT RUNTIME
WebElement ele = driver.findElement(By.xpath(“xpath”)));
((JavascriptExecutor)driver).executeScript(“arguments[0].setAttribute(‘style’,’visibility:visible;’);”,ele);
 */
