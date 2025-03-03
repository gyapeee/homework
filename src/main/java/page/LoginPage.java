package page;

import data.Credential;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Click;
import util.action.Fill;
import util.action.Wait;

public class LoginPage {

    @FindBy(css = "[data-test='username']")
    private WebElement username;

    @FindBy(css = "[data-test='password']")
    private WebElement password;

    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement error;

    public LoginPage() {
        Driver.get().get("https://www.saucedemo.com/inventory.html");
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(username);
        Wait.forVisible(password);
        Wait.forVisible(loginButton);
    }

    public ProductsPage login(Credential credentials) {
        Fill.in(username, credentials.getUsername());
        Fill.in(password, credentials.getPassword());
        Click.on(loginButton);
        return new ProductsPage();
    }

    public LoginPage clickOnLoginButton() {
        Click.on(loginButton);
        return this;
    }

    public String getErrorText() {
        return Wait.forVisible(error).getText();
    }
}
