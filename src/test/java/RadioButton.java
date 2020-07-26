import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RadioButton {
    WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        // chrome driver version = 83.4103.116
        String workingDirectory = System.getProperty("user.dir");
        String filePath =
                workingDirectory
                        + File.separator
                        + File.separator
                        + "\\src\\main\\Resources\\chromedriver.exe";
        System.out.println("Final file path : " + filePath);
        System.setProperty("webdriver.chrome.driver", filePath);

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void radioButtonTest() {
        driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");

        // Click on button to get the selected value
        boolean isMaleRadioButtonEnabled =
                driver.findElement(By.xpath("(//input[@value='Male'])[1]")).isEnabled();
        System.out.println("Radio button for Male enabled is " + isMaleRadioButtonEnabled);

        boolean isFemaleRadioButtonEnabled =
                driver.findElement(By.xpath("(//input[@value='Female'])[1]")).isEnabled();
        System.out.println("Radio button for female enabled is " + isFemaleRadioButtonEnabled);

        driver.findElement(By.xpath("(//input[@value='Male'])[1]")).click();
        driver.findElement(By.id("buttoncheck")).click();
        WebElement expected_message_male =
                driver.findElement(By.xpath("//p[contains(text(),\"Radio button 'Male' is checked\")]"));
        Assert.assertTrue(expected_message_male.isDisplayed());

        driver.findElement(By.xpath("(//input[@value='Female'])[1]")).click();
        driver.findElement(By.id("buttoncheck")).click();
        WebElement expected_message_female =
                driver.findElement(By.xpath("//p[contains(text(),\"Radio button 'Female' is checked\")]"));
        Assert.assertTrue(expected_message_female.isDisplayed());

        driver.findElement(By.xpath("//input[@name='gender' and @value='Male'] ")).click();
        driver.findElement(By.xpath("//input[@name='ageGroup' and @value='0 - 5']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Get values')]")).click();
        System.out.println(driver.findElement(By.cssSelector("p.groupradiobutton")).getText().trim());

        String expected_Selection =
                driver.findElement(By.cssSelector("p.groupradiobutton")).getText().trim();
        Assert.assertTrue(expected_Selection.contains("Sex : Male"));
        Assert.assertTrue(expected_Selection.contains("Age group: 0 - 5"));
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.quit();
    }
}