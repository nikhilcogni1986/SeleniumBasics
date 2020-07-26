import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DatePicker {
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
    public void DatePickerTestApproach1() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("dateOfBirthInput")).click();

        // select the month
        WebElement month_dropdown =
                driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        Select month_select = new Select(month_dropdown);
        month_select.selectByVisibleText("June");

        // select the year
        WebElement year_dropdown =
                driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select year_select = new Select(year_dropdown);
        year_select.selectByValue("2020");

        // get all the dates in an array list
        List<WebElement> dates =
                driver.findElements(By.xpath("//div[@class='react-datepicker__month']//div"));
        System.out.println(dates.size());

        for (int i = 0; i < dates.size(); i++) {
            String date_text = dates.get(i).getText();
            System.out.println(date_text);
            if (date_text.equalsIgnoreCase("8")) {
                dates.get(i).click();
                Thread.sleep(3000);
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
