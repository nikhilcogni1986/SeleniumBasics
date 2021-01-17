import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

public class academyPractice {
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
    public void GreenKartTest() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//input[@placeholder='Search for Vegetables and Fruits']")).sendKeys("Cucumber");

        WebElement productName = driver.findElement(By.xpath("//h4[@class='product-name']"));
        WebDriverWait w1 = new WebDriverWait(driver, 20);
        w1.until(ExpectedConditions.visibilityOf(productName));

        //get the product name
        try {
            if (productName.isDisplayed()) {
                String actual_text = driver.findElement(By.xpath("//h4[@class='product-name']")).getText();
                System.out.println(actual_text);
                Assert.assertTrue(actual_text.contains("Cucumber"));
            }
        } catch (StaleElementReferenceException e) {
            int attempts = 0;
            while (attempts < 2) {
                try {
                    System.out.println("In Try Block");
                    String actual_text = driver.findElement(By.xpath("//h4[@class='product-name']")).getText();
                    Assert.assertTrue(actual_text.contains("Cucumber"));
                } catch (StaleElementReferenceException e1) {
                }
                attempts++;
            }
        }
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}
