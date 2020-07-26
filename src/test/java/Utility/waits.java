package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class waits {
    static WebDriverWait wait;
    static WebElement expected_element;

    public static WebElement waitForElementPresent(WebDriver driver, By element1) {
        if (isElementDisplayed(driver, element1)) {
            wait = new WebDriverWait(driver, 20);
            return wait.until(ExpectedConditions.elementToBeClickable(element1));
        } else
            return null;
    }

    public static boolean isElementDisplayed(WebDriver driver, By element1) {
        wait = new WebDriverWait(driver, 20);
        WebElement expected_element = wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
        System.out.println("found");
        return expected_element.isDisplayed();
    }

    public static void click(WebDriver driver, By element1) {
        try {
            expected_element = waitForElementPresent(driver, element1);
            if (expected_element != null) {
                expected_element.click();
                System.out.println("clicked element");
            }
        } catch (Exception exception1) {
            exception1.getStackTrace();
        }
    }

    public static void elementClick(WebDriver driver, By element) {
        int ok_size = driver.findElements(element).size();
        driver.findElements(element).get(ok_size - 1).click();
    }

    public static void isChecked(WebDriver driver, By element) {
        WebElement expected_element = driver.findElement(element);
        System.out.println("I'm in isChecked Function");
        Assert.assertTrue(expected_element.isSelected());
    }
}