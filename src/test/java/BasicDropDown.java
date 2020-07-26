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

public class BasicDropDown {
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
    public void dropdownTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        WebElement day = driver.findElement(By.id("select-demo"));
        Select S1 = new Select(day);
        S1.selectByIndex(1);
        System.out.println("Select by Index");
        String firstSelectedOption = S1.getFirstSelectedOption().getText();
        System.out.println(firstSelectedOption);
        Thread.sleep(2000);

        // select by value
        System.out.println("Select by Value");
        S1.selectByValue("Monday");
        firstSelectedOption = S1.getFirstSelectedOption().getText();
        System.out.println(firstSelectedOption);
        Thread.sleep(2000);

        System.out.println("Select by Visible Text");
        S1.selectByVisibleText("Tuesday");
        firstSelectedOption = S1.getFirstSelectedOption().getText();
        System.out.println(firstSelectedOption);

        System.out.println("Print all the options");
        List<WebElement> options = S1.getOptions();
        int number_of_options = options.size();
        for (int i = 0; i < number_of_options; i++) {
            String option_Value = S1.getOptions().get(i).getText();
            System.out.println(option_Value);
            if (option_Value.equalsIgnoreCase("Wednesday")) {
                S1.selectByIndex(i);
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
