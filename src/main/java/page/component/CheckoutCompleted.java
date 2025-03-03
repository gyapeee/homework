package page.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Wait;

public class CheckoutCompleted {
    @FindBy(css = "[data-test='checkout-complete-container']")
    private WebElement checkoutComplete;

    @FindBy(css = "[data-test='complete-header']")
    private WebElement header;

    public CheckoutCompleted() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(checkoutComplete);
    }

    public WebElement getHeader() {
        return Wait.forVisible(header);
    }
}
