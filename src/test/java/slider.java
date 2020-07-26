import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class slider {
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
    public void moveSliderTest1() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/drag-drop-range-sliders-demo.html");
        WebElement source = driver.findElement(By.xpath("(//div//input[@name='range'])[1]"));

        WebDriverWait explicitWait = new WebDriverWait(driver, 1000 * 60 * 2);
        WebElement sliderElement = explicitWait.until(ExpectedConditions.elementToBeClickable(source));
        Actions act = new Actions(driver);

        act.clickAndHold(source).moveByOffset(1, 0).release().build().perform();
        // act.dragAndDropBy(source,1,0).release().build().perform();
        Thread.sleep(5000);

        String sliderValue = driver.findElement(By.xpath("//output[@id='range']")).getText().toString();
        System.out.println(sliderValue);
    }

    @Test
    public void moveSliderTest2() throws InterruptedException {
        driver.get("https://jqueryui.com/slider/");
        driver.switchTo().frame(0);
        WebElement sliderHandle = driver.findElement(By.cssSelector("span.ui-slider-handle"));
        Actions act = new Actions(driver);
        act.click(sliderHandle).build().perform();
        // move the slider on right side
        moveTheSlider(10, act);
        Thread.sleep(2000);
    }

    public void moveTheSlider(int numberOfPixelsToMove, Actions act) {
        Keys keys = numberOfPixelsToMove > 0 ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;
        for (int i = 0; i < numberOfPixelsToMove; i++) {
            act.sendKeys(keys).build().perform();
        }
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}