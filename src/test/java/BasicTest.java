import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTest {

    private static final String INCORRECT_LOGIN_HEADER_TITLE = "Incorrect Login Header Title";
    private static final String INCORRECT_TITLE = "Incorrect title";

    @Test
    public void openSwagLabsAndAssertTitle() {
        // Given
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // When
        driver.get("https://www.saucedemo.com/inventory.html");

        // Then
        Assert.assertEquals(driver.findElement(By.cssSelector(".login_logo")).getText(), "Swag Labs", INCORRECT_LOGIN_HEADER_TITLE);
        Assert.assertEquals(driver.getTitle(), "Swag Labs", INCORRECT_TITLE);
        driver.quit();
    }

    @Test
    public void openSwagLabsTest() {
        // Given
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // When
        driver.get("https://onlinehtmleditor.dev");

        // Then
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Online HTML Editor", INCORRECT_LOGIN_HEADER_TITLE);
        Assert.assertEquals(driver.getTitle(), "Free online HTML editor - onlinehtmleditor.dev", INCORRECT_TITLE);
        driver.quit();
    }
}
