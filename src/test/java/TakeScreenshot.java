import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TakeScreenshot {
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
    public void getScreenshot() {
        driver.get("https://www.phptravels.net/home");
        takeScreenshot();
    }

    public void takeScreenshot() {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(
                    file,
                    new File(
                            "F:\\Selenium\\src\\main\\screenshots"
                                    + "sample"
                                    + ""
                                    + System.currentTimeMillis()
                                    + ".png"));

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}
