package util.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import runner.Driver;

import java.time.Duration;

public class Wait {
    private static final ThreadLocal<Wait> instance = new ThreadLocal<>() {
        @Override
        protected Wait initialValue() {
            return new Wait(_5_SECONDS, _200_MILLIS);
        }
    };

    private final FluentWait<WebDriver> fluentWait;
    public static final int _5_SECONDS = 5;
    public static final int _30_SECONDS = 30;
    public static final int _1000_MILLIS = 1000;
    public static final int _200_MILLIS = 200;

    private Wait(long timeoutInSeconds, long pollingInMillis) {
        fluentWait = fluentWait(timeoutInSeconds, pollingInMillis);
    }

    public static void init(long timeoutInSeconds, long pollingInMillis) {
        instance.set(new Wait(timeoutInSeconds, pollingInMillis));
    }

    private static Wait getInstance() {
        if (instance.get() == null) {
            throw new RuntimeException(
                    "Wait is not initialized! Call Wait.init(long timeoutInSeconds, long pollingInMillis) first.");
        }
        return instance.get();
    }

    public static WebElement forVisible(WebElement element) {
        return getInstance().fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement forVisible(By locator) {
        return getInstance().fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement forClickable(WebElement element) {
        return getInstance().fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement forClickable(By locator) {
        return getInstance().fluentWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement forPresence(By locator) {
        return getInstance().fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static FluentWait<WebDriver> fluentWait(long seconds, long millis) {
        return new FluentWait<>(Driver.get()).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(
                Duration.ofMillis(millis)).ignoring(Exception.class);
    }
}
