package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        Driver.set(new ChromeDriver());
        System.setProperty("log4j2.debug", "true");
    }

    @AfterMethod
    public void teardown() {
        Driver.quit();
    }
}
