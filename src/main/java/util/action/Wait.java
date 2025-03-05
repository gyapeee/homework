package util.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import runner.Driver;

import java.time.Duration;

public class Wait {
    private Wait() {
    }

    private static final FluentWait<WebDriver> WAIT_5_SECONDS_POLLING_200_MILLIS =
            new FluentWait<>(Driver.get()).withTimeout(
                    Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200)).ignoring(Exception.class);

    public static WebElement forVisible(WebElement element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement forVisible(By locator) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement forClickable(WebElement element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forClickable(By element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forPresence(By element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
