package page.sauce.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import runner.Driver;
import util.action.Click;
import util.action.Scroll;
import util.action.Wait;

public class CheckoutOverview {
    @FindBy(css = "[data-test='checkout-summary-container']")
    private WebElement checkoutSummaryContainer;

    @FindBy(css = "[data-test='finish']")
    private WebElement finishButton;

    public CheckoutOverview() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(checkoutSummaryContainer);
    }

    public void clickOnFinishButton() {
        Scroll.to(finishButton);
        Click.on(finishButton);
    }
}
