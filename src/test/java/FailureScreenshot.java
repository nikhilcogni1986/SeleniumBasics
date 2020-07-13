import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FailureScreenshot {
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
    public void getTitleTest() {
        driver.get("https://www.phptravels.net/home");
        String expected_title = "PHPTRAVELS | Travel Technology Partner1";
        String actual_title = driver.getTitle();
        System.out.println(actual_title);
        Assert.assertEquals(actual_title, expected_title);
    }

    @AfterTest
    public void tear_down(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                //create a reference for the screenshot
                TakesScreenshot ts = ((TakesScreenshot) driver);
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("F:\\Selenium\\src\\main\\screenshots" + "sample" + "" + System.currentTimeMillis() + ".png"));
            } catch (Exception e1) {
                e1.getStackTrace();
            }
        }

        //closes the browser instance
        driver.quit();
    }
}