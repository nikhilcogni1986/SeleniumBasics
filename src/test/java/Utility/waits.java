package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waits {
    public static WebDriverWait w1 = null;

    public static boolean isElementDisplayed(WebDriver driver, By e1) {
        w1 = new WebDriverWait(driver, 20);
        return (w1.until(ExpectedConditions.visibilityOfElementLocated(e1))).isDisplayed();
    }

    public static void click(WebDriver driver, By e1) {
        w1 = new WebDriverWait(driver, 20);
        boolean elementDisplayed = isElementDisplayed(driver, e1);
        if (elementDisplayed) {
            WebElement element1 = w1.until(ExpectedConditions.elementToBeClickable(e1));
            element1.click();
        }
    }

    public static void retryingFindClick(WebDriver driver, By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }
}
