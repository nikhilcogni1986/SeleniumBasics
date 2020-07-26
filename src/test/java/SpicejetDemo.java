import Utility.waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SpicejetDemo {
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void SpiceJetTest() throws InterruptedException {
        driver.get("https://www.spicejet.com/default.aspx");
        System.out.println(driver.getTitle());

        //select the radio buttons
        By oneWay = By.id("ctl00_mainContent_rbtnl_Trip_0");
        waits.click(driver, oneWay);

        //declaration sections
        By source = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
        By source_City = By.xpath("//a[@text='Bengaluru (BLR)']");
        By destination = By.xpath("(//a[@text='Chennai (MAA)'])[2]");

        By departure_date = By.xpath("//input[@id='ctl00_mainContent_view_date1']");
        By current_date = By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active");

        By family_friends_checkbox = By.cssSelector("input[id*='friendsandfamily']");
        By senior_citizen_checkbox = By.cssSelector("input[id*='SeniorCitizenDiscount']");
        By armed_forces_checkbox = By.cssSelector("input[id*='chk_IndArm']");
        By student_checkbox = By.cssSelector("input[id*='StudentDiscount']");
        By unaccompanied_minor_checkbox = By.cssSelector("input[id*='chk_Unmr']");

        By passengers = By.id("divpaxinfo");
        By adults = By.id("ctl00_mainContent_ddl_Adult");
        // *************************************************************************************************************

        // click on drop down to select the currency
        WebElement currency = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select currency_list = new Select(currency);
        currency_list.selectByVisibleText("AED");

        //Select the source and destination
        waits.click(driver, source);
        waits.click(driver, source_City);
        waits.click(driver, destination);

        boolean text = driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5");
        Assert.assertTrue(text);

        //select the current date as departure date
        waits.click(driver, departure_date);
        waits.click(driver, current_date);

        //select other options for the discount
        waits.click(driver, senior_citizen_checkbox);
        waits.isChecked(driver, senior_citizen_checkbox);

        //select the number of passengers
        waits.click(driver, passengers);
        WebElement adult_webelement = waits.waitForElementPresent(driver, adults);
        Select S1 = new Select(adult_webelement);
        S1.selectByIndex(2);
        waits.click(driver, passengers);

        //click on search
        By search_button = By.xpath("//input[@id='ctl00_mainContent_btn_FindFlights']");
        waits.click(driver, search_button);
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}