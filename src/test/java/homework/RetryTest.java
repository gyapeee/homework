package homework;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;
import util.ConfigProperties;
import util.action.Take;

@Feature("Retry")
public class RetryTest extends TestBase {
    private static final ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    private int runCounter = 0;

    @Test
    @Story("Retry Test")
    @Description("This test verifies the retry feature of the framework")
    public void passesAfterRetriesAndTakesScreenshot() throws InterruptedException {
        Driver.get().get("https://www.saucedemo.com/");
        Thread.sleep(2000);
        // Simulate failures
        if (runCounter < config.retryMax() - 1) {
            runCounter++;
            Assert.fail("This test fails for demonstrating the retry feature");
        }
        Take.screenshot();
    }
}
