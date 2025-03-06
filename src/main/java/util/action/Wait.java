package util.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import runner.Driver;

import java.time.Duration;

public class Wait {
    public static final int _10_SECONDS = 10;
    public static final int _1000_MILLIS = 1000;

    private Wait() {
    }

    private static final FluentWait<WebDriver> WAIT_5_SECONDS_POLLING_200_MILLIS = fluentWait(5, 200);

    private static FluentWait<WebDriver> fluentWait(int seconds, int millis) {
        return new FluentWait<>(Driver.get())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(millis))
                .ignoring(Exception.class);
    }

    public static WebElement forVisible(WebElement element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement forVisible(By locator) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement forVisible(By locator, int seconds, int millis) {
        return fluentWait(seconds, millis).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement forClickable(WebElement element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forClickable(By element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forClickable(By element, int seconds, int millis) {
        return fluentWait(seconds, millis).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forPresence(By element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static WebElement forPresence(By element, int seconds, int millis) {
        return fluentWait(seconds, millis).until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
