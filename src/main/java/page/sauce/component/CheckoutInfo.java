package page.sauce.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Click;
import util.action.Fill;
import util.action.Wait;

public class CheckoutInfo {
    @FindBy(css = "[data-test='checkout-info-container']")
    private WebElement checkoutInfoContainer;

    @FindBy(css = "[data-test='firstName']")
    private WebElement firstNameElement;

    @FindBy(css = "[data-test='lastName']")
    private WebElement lastNameElement;

    @FindBy(css = "[data-test='postalCode']")
    private WebElement postalCodeElement;

    @FindBy(css = "[data-test='continue']")
    private WebElement continueButton;

    public CheckoutInfo() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(checkoutInfoContainer);
        Wait.forVisible(firstNameElement);
        Wait.forVisible(lastNameElement);
        Wait.forVisible(postalCodeElement);
    }

    public CheckoutInfo fillInfo(String firstName, String lastName, String postalCode) {
        Fill.in(firstNameElement, firstName);
        Fill.in(lastNameElement, lastName);
        Fill.in(postalCodeElement, postalCode);
        return this;
    }

    public void clickContinueButton() {
        Click.on(continueButton);
    }
}
