package util.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import runner.Driver;

import java.time.Duration;

public class Wait {

    private static final FluentWait<WebDriver> WAIT_5_SECONDS_POLLING_200_MILLIS =
            new FluentWait<>(Driver.get()).withTimeout(
                    Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200)).ignoring(Exception.class);

    public static WebElement forVisible(WebElement element) {
        return WAIT_5_SECONDS_POLLING_200_MILLIS.until(ExpectedConditions.visibilityOf(element));
    }
}
