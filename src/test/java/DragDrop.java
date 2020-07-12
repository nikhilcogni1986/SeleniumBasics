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

public class DragDrop {
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
    public void dragDropTestOption1() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        Actions act = new Actions(driver);
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        act.dragAndDrop(drag, drop).build().perform();
        Thread.sleep(3000);
    }

    @Test
    public void dragDropTestOption2() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        driver.findElement(By.id("droppableExample-tab-accept")).click();
        Thread.sleep(3000);
        Actions act1 = new Actions(driver);
        WebElement drag = driver.findElement(By.xpath("(//div[contains(text(),'Acceptable')])[1]"));
        WebElement drop = driver.findElement(By.xpath("(//div[@id='droppable'])[2]"));

        //get offset for drag element
        int xOffset1 = drag.getLocation().getX();
        int yOffset1 = drag.getLocation().getY();

        System.out.println("xOffset1--->" + xOffset1 + " yOffset1--->" + yOffset1);

        //get offset for drop element
        int xOffset = drop.getLocation().getX();
        int yOffset = drop.getLocation().getY();
        System.out.println("xOffset--->" + xOffset + " yOffset--->" + yOffset);

        //Find the xOffset and yOffset difference to find x and y offset needed in which from object
        // required to dragged and dropped
        xOffset = (xOffset - xOffset1) + 10;
        yOffset = (yOffset - yOffset1) + 20;

        act1.dragAndDropBy(drag, xOffset, yOffset).perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@id='droppable']//p)[2]")).isDisplayed());
        Thread.sleep(3000);
    }

    @Test
    public void dragDropTestOption3() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        driver.findElement(By.id("droppableExample-tab-preventPropogation")).click();

        WebElement source = driver.findElement(By.id("dragBox"));
        WebElement outer_draggable = driver.findElement(By.xpath("//div[@id='notGreedyDropBox']"));

        Actions act = new Actions(driver);
        act.dragAndDrop(source, outer_draggable).build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='notGreedyDropBox']//p")).isDisplayed());
    }

    @Test
    public void dragDropTestOption4() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        driver.findElement(By.id("droppableExample-tab-preventPropogation")).click();

        WebElement source = driver.findElement(By.id("dragBox"));
        WebElement inner_draggable = driver.findElement(By.xpath("//div[@id='notGreedyInnerDropBox']//p"));

        Actions act = new Actions(driver);
        act.dragAndDrop(source, inner_draggable).build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='notGreedyInnerDropBox']//p")).isDisplayed());
    }

    @Test
    public void dragDropTestOption5() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        driver.findElement(By.id("droppableExample-tab-revertable")).click();

        WebElement will_revert = driver.findElement(By.xpath("//div[@id='revertable']"));
        WebElement destination = driver.findElement(By.xpath("//div[@id='droppableExample-tabpane-revertable']//p[contains(text(),'Drop here')]"));

        Actions act = new Actions(driver);
        act.dragAndDrop(will_revert, destination).build().perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droppable']//p[contains(text(),'Dropped')]")).isDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        //closes the browser instance
        driver.close();
    }
}
