package runner;

import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import util.ConfigProperties;

@Slf4j
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    private int retryCount = 0;
    private final static int retryMax;

    static {
        retryMax = config.retryMax();
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < retryMax) {
            retryCount++;
            log.info("Thread: %s | Retry attempt #" + retryCount + " of " + retryMax);
            return true;
        }
        return false;
    }
}
