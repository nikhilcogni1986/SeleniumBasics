import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HandleWindows {
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
    public void multipleWindowTest() {
        driver.get("https://www.naukri.com/");
        // It will return the parent window name as a String
        String mainWindow = driver.getWindowHandle();

        // It returns no. of windows opened by WebDriver and will return Set of Strings
        Set<String> window = driver.getWindowHandles();

        // Using Iterator to iterate with in windows
        Iterator<String> itr = window.iterator();

        //loop through the windows
        while (itr.hasNext()) {
            String childWindow = itr.next();
            // Compare whether the main windows is not equal to child window. If not equal, we will close.
            if (!mainWindow.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
                driver.close();
            }
        }
        // This is to switch to the main window

        driver.switchTo().window(mainWindow);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}