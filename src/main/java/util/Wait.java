package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import runner.Driver;

import java.time.Duration;

public class Wait {

    public static final FluentWait<WebDriver> WAIT_FIVE_SECONDS = new FluentWait<>(Driver.get()).withTimeout(
            Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

    public static WebElement forVisible(By locator) {
        return WAIT_FIVE_SECONDS.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
