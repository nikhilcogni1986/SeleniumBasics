import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;

public class FirefoxProfiles {
    WebDriver driver;

    @Test
    public void firefoxBrowserLaunch() throws InterruptedException {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile firefox_profile = profile.getProfile("SeleniumWebDriverProfile");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(FirefoxDriver.PROFILE, firefox_profile);
        options.setAcceptInsecureCerts(true);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\geckodriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.gecko.driver", filePath);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        System.out.println(driver.getTitle());
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.quit();
    }
}
