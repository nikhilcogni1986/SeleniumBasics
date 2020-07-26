import Utility.waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class calendarTest {
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
        System.out.println(driver.getTitle());
        driver.get("https://www.spicejet.com/default.aspx");
        // April 23
        By travel_date = By.id("ctl00_mainContent_view_date1");
        waits.click(driver, travel_date);

        List<WebElement> month = driver.findElements(By.xpath("//span[@class='ui-datepicker-month']"));
        int size = month.size();

        for (int i = 0; i < size; i++) {
            System.out.println("i value: " + i);
            String month_name = month.get(i).getText();
            if (!month_name.contains("August")) {
                System.out.println("Month Name--> " + month_name);
                driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
            }
        }
        //    while (!driver
        //        .findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']"))
        //        .getText()
        //        .contains("May")) {
        //      driver.findElement(By.cssSelector("[class='datepicker-days']
        // th[class='next']")).click();
        //    }
        //
        //    List<WebElement> dates = driver.findElements(By.className("day"));
        //    // Grab common attribute//Put into list and iterate
        //    int count = driver.findElements(By.className("day")).size();
        //
        //    for (int i = 0; i < count; i++) {
        //      String text = driver.findElements(By.className("day")).get(i).getText();
        //      if (text.equalsIgnoreCase("21")) {
        //        driver.findElements(By.className("day")).get(i).click();
        //        break;
        //      }
        //    }
    }
}
