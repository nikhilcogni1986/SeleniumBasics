import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class FirefoxTest {
    WebDriver driver;

    @Test
    public void firefoxBrowserLaunch() {

        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\geckodriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.gecko.driver", filePath);

        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.quit();

    }
}