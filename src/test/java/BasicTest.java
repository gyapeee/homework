import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;

public class BasicTest extends TestBase {

    private static final String INCORRECT_LOGIN_HEADER_TITLE = "Incorrect Login Header Title";
    private static final String INCORRECT_TITLE = "Incorrect title";

    @Test
    public void openSwagLabsAndAssertTitle() {
        // Given
        // When
        Driver.get().get("https://www.saucedemo.com/inventory.html");

        // Then
        Assert.assertEquals(Driver.get().findElement(By.cssSelector(".login_logo")).getText(), "Swag Labs",
                INCORRECT_LOGIN_HEADER_TITLE);
        Assert.assertEquals(Driver.get().getTitle(), "Swag Labs", INCORRECT_TITLE);
    }

    @Test
    public void openSwagLabsTest() {
        // Given
        // When
        Driver.get().get("https://onlinehtmleditor.dev");

        // Then
        Assert.assertEquals(Driver.get().findElement(By.tagName("h1")).getText(), "Online HTML Editor",
                INCORRECT_LOGIN_HEADER_TITLE);
        Assert.assertEquals(Driver.get().getTitle(), "Free online HTML editor - onlinehtmleditor.dev", INCORRECT_TITLE);
    }
}
