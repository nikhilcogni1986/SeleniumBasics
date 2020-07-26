import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GetLinks {
    public static void main(String[] args) throws InterruptedException {
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
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.getTitle());

        // get the links from entire page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        // get the links from specific portion of page
        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
        List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
        System.out.println(footerLinks.size());

        // get the links from 1st column of footer
        WebElement firstColumnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        List<WebElement> firstColumnFooterLinks = firstColumnDriver.findElements(By.tagName("a"));
        System.out.println(firstColumnFooterLinks.size());

        // click on each link starting with 2nd link in separate tab
        for (int i = 1; i < firstColumnFooterLinks.size(); i++) {
            String clickCtrlEnter = Keys.chord(Keys.CONTROL, Keys.ENTER);
            firstColumnFooterLinks.get(i).sendKeys(clickCtrlEnter);
            Thread.sleep(2000);
        }
        // get the titles of each child tab or window
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            driver.switchTo().window(iterator.next());
            System.out.println(driver.getTitle());
        }
        driver.quit();
    }
}
