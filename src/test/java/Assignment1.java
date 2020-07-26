import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Assignment1 {
    public static void main(String[] args) throws InterruptedException {
        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.getTitle());

        // select the checkbox option2
        driver.findElement(By.id("checkBoxOption2")).click();
        String optionSelected = driver.findElement(By.id("checkBoxOption2")).getAttribute("value");
        System.out.println(optionSelected);

        // select the option from drop down with text grabbed from checkbox
        WebElement dropDown = driver.findElement(By.id("dropdown-class-example"));
        Select S1 = new Select(dropDown);
        S1.selectByValue(optionSelected);

        // enter the optionselected from checkbox in alert text box
        driver.findElement(By.id("name")).sendKeys(optionSelected);
        driver.findElement(By.id("alertbtn")).click();
        String alert_text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertTrue(alert_text.contains(optionSelected));
        driver.quit();
    }
}
