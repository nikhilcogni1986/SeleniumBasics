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

public class AutoSuggestions {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void autoSuggestionsTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        System.out.println(driver.getTitle());

        // Search Ind and Select India
        driver.findElement(By.id("autosuggest")).sendKeys("Ind");
        Thread.sleep(2000);
        List<WebElement> auto_options =
                driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement auto_option : auto_options) {
            String option_text = auto_option.getText();
            if (option_text.equalsIgnoreCase("India")) {
                auto_option.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}
