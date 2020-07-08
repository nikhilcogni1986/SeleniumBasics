import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeTest {
    public static void main(String[] args) {
        //project directory
        //chrome driver version = 83.4103.116
        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\chromedriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.chrome.driver", filePath);

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
        driver.quit();
    }
}