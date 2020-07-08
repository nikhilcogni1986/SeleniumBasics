import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class checkboxes {
    WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        //chrome driver version = 83.4103.116
        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\chromedriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.chrome.driver", filePath);

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkboxTest() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        //check the first checkbox and verify if its checked already

        boolean ischecked = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected();
        System.out.println("Checkbox selected is " + ischecked);

        boolean isCheckboxEnabled = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isEnabled();
        System.out.println("Checkbox enabled is " + isCheckboxEnabled);

        if (!ischecked)
            driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        else
            System.out.println("Checkbox is already selected");

    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.quit();
    }
}
