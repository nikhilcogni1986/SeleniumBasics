import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FluentWaitExample {
    public static void main(String[] args) {
        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", filePath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println(driver.getTitle());

        // click on start button
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

        // implement fluent wait
        Wait<WebDriver> wait =
                new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(NoSuchElementException.class);

        WebElement foo =
                wait.until(
                        new Function<WebDriver, WebElement>() {
                            public WebElement apply(WebDriver driver) {
                                if (driver
                                        .findElement(By.xpath("//h4[contains(text(),'Hello World!')]"))
                                        .isDisplayed())
                                    return driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
                                else return null;
                            }
                        });
        System.out.println(foo.getText());
        driver.quit();
    }
}
