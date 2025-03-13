package homework;

import com.google.gson.Gson;
import data.CheckoutUser;
import data.Credential;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.sauce.LoginPage;
import page.sauce.ProductsPage;
import page.sauce.component.CheckoutCompleted;
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

    @Test(dataProvider = "loadCheckoutUsersJson")
    @Feature("Homework")
    @Story("Sauce Demo Test")
    @Description("Verifies purchase process of the sauce lab's demo page")
    public void automatePurchaseProcess_Test_1(CheckoutUser user) {
        // Given
        logStep("Logging in");
        new LoginPage().login(credentials[PERFORMANCE_GLITCH_USER_INDEX]);

        // When
        logStep("Adding items to cart");
        ProductsPage productsPage = new ProductsPage().clickOnAddItemButtons(
                List.of("add-to-cart-sauce-labs-backpack", "add-to-cart-sauce-labs-bike-light"));

        // Then
        Assert.assertEquals(productsPage.numberOfItemsInCart(), EXPECTED_NUMBER_OF_ITEMS,
                            "Number of items in cart should be " + EXPECTED_NUMBER_OF_ITEMS);

        // And When
        logStep("Checking out products");
        CheckoutCompleted completed = productsPage.checkout(user);

        // Then
        Assert.assertEquals(completed.getHeader().getText(), THANK_YOU_FOR_YOUR_ORDER,
                            "Header text should be: " + THANK_YOU_FOR_YOUR_ORDER);
    }

    @Test
    @Feature("Homework")
    @Story("Sauce Demo Test")
    @Description("Verifies login error on sauce lab's demo page")
    public void verifyLoginErrorAndFooter_Test_2() {
        // Given
        // When
        logStep("Verifying error message");
        LoginPage loginPage = new LoginPage().clickOnLoginButton();

        // Then
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required", "Error message is wrong");

        // And When
        logStep("Logging in");
        ProductsPage productsPage = loginPage.login(credentials[STANDARD_USER_INDEX]);

        logStep("Verifying footer data");
        Assert.assertTrue(productsPage.getFooter().getCopyRight().getText().contains(YEAR_2025),
                          YEAR_2025 + " is missing from the footer");
        Assert.assertTrue(productsPage.getFooter().getCopyRight().getText().contains(TERMS_OF_SERVICE),
                          TERMS_OF_SERVICE + " is missing from the footer");
    }

    private static Credential[] loadCredentialsJson() throws IOException {
        return getJson(Credential[].class, "../data/credentials.json");
    }

    @DataProvider(name = "loadCheckoutUsersJson")
    private static CheckoutUser[] loadCheckoutUsersJson() throws IOException {
        return getJson(CheckoutUser[].class, "../data/checkout_users.json");
    }

    private static <T> T getJson(Class<T> dataType, String file) throws IOException {
        InputStream credentialsStream = SauceDemoTest.class.getResourceAsStream(file);
        InputStreamReader credentialsReader = new InputStreamReader(Objects.requireNonNull(credentialsStream));

        T credentials = new Gson().fromJson(credentialsReader, dataType);

        credentialsReader.close();
        return credentials;
    }
}
