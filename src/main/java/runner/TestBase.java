package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.ConfigProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Test(retryAnalyzer = RetryAnalyzer.class)
public abstract class TestBase {
    static ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        // Resolve driver for Selenium Grid
        WebDriverManager.chromedriver().setup();

        // Start Selenium Grid in standalone mode
        Main.main(new String[]{
                "standalone",
                "--port",
                "4445"
        });
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriver driver = WebDriverManager.chromedriver().remoteAddress("http://localhost:4445/wd/hub").capabilities(
                new DesiredCapabilities().merge(options())).create();
        Driver.set(driver);
        Driver.get().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        Driver.quit();
    }

    synchronized private static ChromeOptions options() {
        List<String> arguments = new ArrayList<>();

        arguments.add("--no-sandbox");
        arguments.add("--user-data-dir=/tmp/chrome-user-data-" + ThreadLocalRandom.current().nextLong());
        arguments.add("--incognito");
        arguments.add("--disable-quic");
        arguments.add("--disable-search-engine-choice-screen");
        arguments.add("--remote-debugging-pipe");

        if (config.disableDevShmUsage()) {
            arguments.add("--disable-dev-shm-usage");
        }
        if (config.headless()) {
            arguments.add("--headless=new");
        }
        if (config.disableGpu()) {
            arguments.add("--disable-gpu");
        }

        return new ChromeOptions().addArguments(arguments);
    }

    @Step("{0}")
    protected void logStep(String message) {
        log.info(message);
    }

}
