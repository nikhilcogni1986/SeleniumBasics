import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BootstrapDropdown {
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
    public void bootStrapTest() throws InterruptedException {
        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

        //click on the drop down
        driver.findElement(By.xpath("//button[@class='multiselect dropdown-toggle btn btn-default']")).click();

        //get the elements from the drop down
        driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();
        Thread.sleep(2000);
        List<WebElement> list_of_options = driver.findElements(By.xpath("//ul[contains(@class,'multiselect-container dropdown-menu')]//li//a//label"));

        int number_options = list_of_options.size();
        System.out.println("Number of options in drop down: " + list_of_options.size());

        //for loop to iterate and find angular element
        for (WebElement list_of_option : list_of_options) {
            System.out.println(list_of_option.getText());
            if (list_of_option.getText().contains("Angular")) {
                list_of_option.click();
                Thread.sleep(3000);
                break;
            }
        }
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}
