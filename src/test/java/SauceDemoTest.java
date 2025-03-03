import com.google.gson.Gson;
import data.Credentials;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.ProductsPage;
import page.component.CheckoutCompleted;
import runner.Driver;
import runner.TestBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Slf4j
public class SauceDemoTest extends TestBase {

    private static final String INCORRECT_LOGIN_HEADER_TITLE = "Incorrect Login Header Title";
    private static final String INCORRECT_TITLE = "Incorrect title";
    private static final Credentials credentials;
    private static final int EXPECTED_NUMBER_OF_ITEMS = 2;
    private static final String THANK_YOU_FOR_YOUR_ORDER = "Thank you for your order!";

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
        new LoginPage().login(credentials);

        // When
        ProductsPage productsPage = new ProductsPage().clickOnAddItemButtons(
                List.of("add-to-cart-sauce-labs-backpack",
                        "add-to-cart-sauce-labs-bike-light"));

        // Then
        Assert.assertEquals(productsPage.numberOfItemsInCart(), EXPECTED_NUMBER_OF_ITEMS,
                "Number of items in cart should be " + EXPECTED_NUMBER_OF_ITEMS);

        // And When
        CheckoutCompleted completed = productsPage.checkout("John", "Doe", "6898");

        // Then
        Assert.assertEquals(completed.getHeader().getText(), THANK_YOU_FOR_YOUR_ORDER,
                "Header text should be: " + THANK_YOU_FOR_YOUR_ORDER);
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
