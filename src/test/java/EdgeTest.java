import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class EdgeTest {

    WebDriver driver;

    @Test
    public void edgeBrowserLaunch() {
        // edge driver version = 83.4103.116
        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\msedgedriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.edge.driver", filePath);

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.quit();
    }
}
