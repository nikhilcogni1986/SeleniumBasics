import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GreenKart {
    WebDriver driver;

    @BeforeTest
    public void setDriver() {
        String workingDirectory = System.getProperty("user.dir");
        String filePath = workingDirectory + File.separator + File.separator + "\\src\\main\\Resources\\geckodriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.gecko.driver", filePath);

        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void GreenKartTest() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        System.out.println(driver.getTitle());

        //get the product name
        By productName = By.xpath("//h4[@class='product-name']");
        List<WebElement> products = driver.findElements(productName);
        System.out.println("Number of products on the page are --> " + products.size());

        //capture required product names in array and then convert to List
        String[] expected_name = {"Cucumber", "Brocolli"};

        //loop to iterate for clicking on ADD TO CART
        for (int i = 0; i < products.size(); i++) {
            String name = products.get(i).getText();
            System.out.println(name);
            List<String> itemsNeeded = Arrays.asList(expected_name);
            if (itemsNeeded.contains(name)) {
                driver.findElements(By.xpath("//button[contains(text(),'ADD TO CART')]")).get(i).click();
            }
        }
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}
