package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runner.Driver;
import util.action.Click;
import util.action.Do;
import util.action.Fill;
import util.action.Scroll;
import util.action.Wait;

import static util.action.Wait._1000_MILLIS;
import static util.action.Wait._10_SECONDS;

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
        Scroll.to(Wait.forPresence(IFRAME_ID, _10_SECONDS, _1000_MILLIS));
        Do.underIFrame(IFRAME_ID, () -> Click.on(JMETER_AD_IMAGE, _10_SECONDS, _1000_MILLIS));
    }

    public void fillAndSubmitEmail(String email) {
        Scroll.to(Wait.forPresence(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS));
        WebElement emailInput = Wait.forVisible(EMAIL_INPUT, _10_SECONDS, _1000_MILLIS);
        Fill.in(emailInput, email);
        Click.on(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS);
    }

    public void navigateToSeleniumTooltip() {
        Click.on(SELENIUM_MENU, _10_SECONDS, _1000_MILLIS);
        Click.on(TOOLTIP_OPTION, _10_SECONDS, _1000_MILLIS);
    }

    public WebElement getDownloadNowElement() {
        return Driver.get().findElement(DOWNLOAD_NOW);
    }

    public By getPostElement() {
        return POST_ELEMENT;
    }
}
