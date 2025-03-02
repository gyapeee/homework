import com.google.gson.Gson;
import data.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;
import util.Fill;
import util.Wait;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static util.Locator.dataTest;

@Slf4j
public class SauceDemoTest extends TestBase {

    private static final String INCORRECT_LOGIN_HEADER_TITLE = "Incorrect Login Header Title";
    private static final String INCORRECT_TITLE = "Incorrect title";
    private static final Credentials credentials;

    static {
        try {
            credentials = loadCredentialsJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void automatePurchaseProcessTest1() {
        // Given
        Driver.get().get("https://www.saucedemo.com/inventory.html");

        Wait.forVisible(By.cssSelector(".login_logo"));

        Fill.in(Wait.forVisible(dataTest("username")), credentials.getUsername());
        Fill.in(Wait.forVisible(dataTest("password")), credentials.getPassword());

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

    private static Credentials loadCredentialsJson() throws IOException {
        InputStream credentialsStream = SauceDemoTest.class.getResourceAsStream("credentials.json");
        InputStreamReader credentialsReader = new InputStreamReader(Objects.requireNonNull(credentialsStream));

        Credentials credentials = new Gson().fromJson(credentialsReader, Credentials.class);

        credentialsReader.close();
        return credentials;
    }
}
