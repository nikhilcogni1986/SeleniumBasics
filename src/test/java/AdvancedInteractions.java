import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AdvancedInteractions {
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
    public void moveToElementTest() throws InterruptedException {
        driver.get("https://www.phptravels.net/home");
        WebElement company_tab = driver.findElement(By.xpath("//li[@class='text-center']//a"));
        WebElement contact_us = driver.findElement(By.linkText("Contact"));
        Actions act = new Actions(driver);
        act.moveToElement(company_tab)
                .pause(1000)
                .moveToElement(contact_us)
                .click(contact_us)
                .build()
                .perform();
        Thread.sleep(4000);
    }

    @Test
    public void rightClickTest() throws InterruptedException {
        driver.get("https://demoqa.com/buttons");
        WebElement right_click_btn = driver.findElement(By.id("rightClickBtn"));

        Actions act = new Actions(driver);
        act.contextClick(right_click_btn).perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.id("rightClickMessage")).isDisplayed());
    }

    @Test
    public void doubleClickTest() throws InterruptedException {
        driver.get("https://demoqa.com/buttons");
        WebElement double_click_btn = driver.findElement(By.id("doubleClickBtn"));

        Actions act = new Actions(driver);
        act.doubleClick(double_click_btn).perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.id("doubleClickMessage")).isDisplayed());
    }

    @Test
    public void mouseHoverMenu() throws InterruptedException {
        driver.get("https://demoqa.com/menu/#");
        WebElement main_menu_item2 =
                driver.findElement(By.xpath("//a[contains(text(),'Main Item 2')]"));
        WebElement sublist = driver.findElement(By.xpath("//a[contains(text(),'SUB SUB LIST')]"));
        WebElement sublist_item1 =
                driver.findElement(By.xpath("//a[contains(text(),'Sub Sub Item 1')]"));

        Actions act = new Actions(driver);
        act.moveToElement(main_menu_item2)
                .pause(1000)
                .moveToElement(sublist)
                .pause(1000)
                .moveToElement(sublist_item1)
                .build()
                .perform();
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeDriver() {
        // closes the browser instance
        driver.close();
    }
}