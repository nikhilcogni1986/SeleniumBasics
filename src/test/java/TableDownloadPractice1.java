import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TableDownloadPractice1 {
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
        driver.get("https://www.seleniumeasy.com/test/table-data-download-demo.html");

        // get the number of rows
        int row_count = driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();

        // Iterate with number of rows to and get the column text for specific column
        for (int i = 1; i < row_count; i++) {
            int column_count =
                    driver.findElements(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td")).size();
            for (int j = 1; j < column_count; j++) {
                String name =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[1]"))
                                .getText();
                String position =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[2]"))
                                .getText();
                String office =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[3]"))
                                .getText();
                String age =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[4]"))
                                .getText();
                String start_date =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[5]"))
                                .getText();
                String salary =
                        driver
                                .findElement(By.xpath("//table[@id='example']//tbody//tr[" + i + "]/td[6]"))
                                .getText();

                System.out.println(
                        name
                                + " "
                                + "joined EY on"
                                + start_date
                                + " "
                                + "as "
                                + position
                                + " "
                                + "reported at "
                                + office
                                + " "
                                + "and currently holds"
                                + " "
                                + salary);
                break;
            }
        }
        driver.quit();
    }
}
