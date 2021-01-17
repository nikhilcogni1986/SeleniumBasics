import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PrintLinks {
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
    public void printLinksTest() throws InterruptedException {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of Links: " + links.size());

        //get the footer driver
        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));

        List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
        System.out.println("Number of Links in footer are: " + footerLinks.size());

        //get the first column and assign a driver to it
        WebElement columndriver = driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td/ul"));
        List<WebElement> columnLinks = columndriver.findElements(By.tagName("a"));
        System.out.println(columnLinks.size());

        //click on each link which opens in separate tab and grab the title
        for (int i = 0; i < columnLinks.size(); i++) {
            String control_click = Keys.chord(Keys.CONTROL, Keys.ENTER);
            Thread.sleep(2000);
            columnLinks.get(i).sendKeys(control_click);
        }

        Set<String> childTabs = driver.getWindowHandles();
        Iterator<String> iterator = childTabs.iterator();
        while (iterator.hasNext()) {
            driver.switchTo().window(iterator.next());
            System.out.println(driver.getTitle());
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
