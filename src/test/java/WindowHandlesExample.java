import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandlesExample {
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
    public void multipleWindowTest() {
        driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
        driver.findElement(By.cssSelector(".blinkingText")).click();
        Set<String> windows = driver.getWindowHandles();

        Iterator<String> iterator = windows.iterator();
        String parentID = iterator.next();
        String childID = iterator.next();

        //switch to child window
        driver.switchTo().window(childID);
        String extractedText = driver.findElement(By.xpath("//p[@class='im-para red']")).getText();
        System.out.println(extractedText);
        String[] ats = extractedText.split(" ");
        driver.switchTo().window(parentID);
        System.out.println(ats[4]);
        driver.findElement(By.id("username")).sendKeys(ats[1]);
        driver.close();
    }

    @Test
    public void multipleWindowsTest() {
        driver.get("https://jqueryui.com/droppable/");
        WebElement frameWeblement = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(frameWeblement);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        Actions act = new Actions(driver);
        act.dragAndDrop(draggable, droppable).build().perform();

        driver.close();
    }

}