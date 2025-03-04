package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBase {
    static ConfigProperties config = ConfigFactory.create(ConfigProperties.class);

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver()
                .setup();
        Driver.set(new ChromeDriver(options()));
        Driver.get().manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        Driver.quit();
    }

    private static ChromeOptions options() {
        List<String> arguments = new ArrayList<>();

        arguments.add("--no-sandbox");
        arguments.add("--user-data-dir=/tmp/chrome-user-data");
        arguments.add("--incognito");

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
}
