import com.google.gson.Gson;
import data.Credential;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import page.ProductsPage;
import page.component.CheckoutCompleted;
import runner.TestBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Slf4j
public class SauceDemoTest extends TestBase {

    private static final Credential[] credentials;
    private static final int EXPECTED_NUMBER_OF_ITEMS = 2;
    private static final String THANK_YOU_FOR_YOUR_ORDER = "Thank you for your order!";
    private static final int STANDARD_USER_INDEX = 1;
    private static final int PERFORMANCE_GLITCH_USER_INDEX = 0;
    private static final String YEAR_2025 = "2025";
    private static final String TERMS_OF_SERVICE = "Terms of Service";

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
        new LoginPage().login(credentials[PERFORMANCE_GLITCH_USER_INDEX]);

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
    public void verifyLoginErrorAndFooterTest2() {
        // Given
        // When
        LoginPage loginPage = new LoginPage().clickOnLoginButton();

        // Then
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required", "Error message is wrong");

        // And When
        ProductsPage productsPage = loginPage.login(credentials[STANDARD_USER_INDEX]);

        Assert.assertTrue(productsPage.getFooter().getCopyRight().getText().contains(YEAR_2025),
                YEAR_2025 + " is missing from the footer");
        Assert.assertTrue(productsPage.getFooter().getCopyRight().getText().contains(TERMS_OF_SERVICE),
                TERMS_OF_SERVICE + " is missing from the footer");
    }

    private static Credential[] loadCredentialsJson() throws IOException {
        InputStream credentialsStream = SauceDemoTest.class.getResourceAsStream("credentials.json");
        InputStreamReader credentialsReader = new InputStreamReader(Objects.requireNonNull(credentialsStream));

        Credential[] credentials = new Gson().fromJson(credentialsReader, Credential[].class);

        credentialsReader.close();
        return credentials;
    }
}
