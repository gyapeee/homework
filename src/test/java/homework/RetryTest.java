package homework;

import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.TestBase;
import util.ConfigProperties;

public class RetryTest extends TestBase {
    private static final ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    private int runCounter = 0;

    @Test
    @Description("This test verifies the retry feature of the framework")
    public void retryDemoRichText() throws InterruptedException {
        Thread.sleep(2000);
        // Simulate failures
        if (runCounter < config.retryMax() - 1) {
            runCounter++;
            Assert.fail("This test fails for demonstrating the retry feature");
        }
    }
}
