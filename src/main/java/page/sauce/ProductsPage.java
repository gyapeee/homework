package page.sauce;

import data.CheckoutUser;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.sauce.component.CheckoutCompleted;
import page.sauce.component.CheckoutInfo;
import page.sauce.component.CheckoutOverview;
import page.sauce.component.Footer;
import runner.Driver;
import util.action.Click;
import util.action.Scroll;
import util.action.Wait;

import java.util.List;
import java.util.Objects;

public class ProductsPage {
    @Getter
    private final Footer footer;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    private WebElement shoppingCartBadge;

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement shoppingCartLink;

    @FindBy(css = "[data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//button[contains(@data-test,'add-to-cart')]")
    private List<WebElement> addToCartButtons;

    public ProductsPage() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(shoppingCartLink);
        footer = new Footer();
    }

    public ProductsPage clickOnAddItemButtons(List<String> itemLocators) {
        itemLocators.forEach(itemLocator -> addToCartButtons.stream().filter(
                element -> Objects.requireNonNull(element.getDomAttribute("data-test")).contains(
                        itemLocator)).findAny().orElseThrow(
                () -> new ButtonCannotFindException(Objects.requireNonNull(itemLocator), itemLocators)).click());

        return this;
    }

    public int numberOfItemsInCart() {
        return Integer.parseInt(Wait.forVisible(shoppingCartBadge).getText());
    }

    public CheckoutCompleted checkout(CheckoutUser user) {
        Click.on(shoppingCartLink);
        Scroll.to(checkoutButton);
        Click.on(checkoutButton);
        new CheckoutInfo().fillInfo(user).clickContinueButton();
        new CheckoutOverview().clickOnFinishButton();
        return new CheckoutCompleted();
    }

    private static class ButtonCannotFindException extends RuntimeException {
        public ButtonCannotFindException(String locator, List<String> itemLocators) {
            super("Cannot find button " + locator + " in " + itemLocators);
        }
    }
}
