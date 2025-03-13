package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runner.Driver;
import util.action.Click;
import util.action.Do;
import util.action.Fill;
import util.action.Scroll;
import util.action.Wait;


public class DemoGuru99Page {
    private static final By IFRAME_ID = By.id("a077aa5e");
    private static final By JMETER_AD_IMAGE = By.cssSelector("[src='Jmeter720.png']");
    private static final By EMAIL_INPUT = By.id("philadelphia-field-email");
    private static final By SUBMIT_BUTTON = By.id("philadelphia-field-submit");
    private static final By SELENIUM_MENU = By.xpath("//a[contains(text(),'Selenium')]");
    private static final By TOOLTIP_OPTION = By.xpath("//a[contains(text(),'Tooltip')]");
    private static final By DOWNLOAD_NOW = By.id("download_now");
    private static final By POST_ELEMENT = By.id("post-542");

    public void interactWithIframeAd() {
        Scroll.to(Wait.forPresence(IFRAME_ID));
        Do.underIFrame(IFRAME_ID, () -> Click.on(JMETER_AD_IMAGE));
    }

    public void fillAndSubmitEmail(String email) {
        Scroll.to(Wait.forPresence(SUBMIT_BUTTON));
        WebElement emailInput = Wait.forVisible(EMAIL_INPUT);
        Fill.in(emailInput, email);
        Click.on(SUBMIT_BUTTON);
    }

    public void navigateToSeleniumTooltip() {
        Click.on(SELENIUM_MENU);
        Click.on(TOOLTIP_OPTION);
    }

    public WebElement getDownloadNowElement() {
        return Driver.get().findElement(DOWNLOAD_NOW);
    }

    public By getPostElement() {
        return POST_ELEMENT;
    }
}
