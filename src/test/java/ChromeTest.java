import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class ChromeTest {

    WebDriver driver;

    @Test
    public void chromeBrowserLaunch() {
        //chrome driver version = 83.4103.116
        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\chromedriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.chrome.driver", filePath);

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.quit();
    }
}