package homework;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;
import util.action.Click;
import util.action.Do;
import util.action.Fill;
import util.action.Scroll;
import util.action.Wait;

import static runner.Driver.lastWindowHandle;
import static util.action.Wait._1000_MILLIS;
import static util.action.Wait._10_SECONDS;

public class DemoGuru99Test extends TestBase {

    private static final By IFRAME_ID = By.id("a077aa5e");
    private static final By SUBMIT_BUTTON = By.id("philadelphia-field-submit");
    private static final By JMETER_AD_IMAGE = By.cssSelector("[src='Jmeter720.png']");
    private static final By EMAIL_INPUT = By.id("philadelphia-field-email");

    private static final String URL = "http://demo.guru99.com/test/guru99home";
    private static final String SUCCESSFULLY = "Successfully";
    private static final String OPENING_URL = "Opening url ";
    private static final String SCROLLING_TO = "Scrolling to ";
    public static final String EMAIL = "user@randommail.com";
    public static final By SELENIUM_MENU = By.xpath("//a[contains(text(),'Selenium')]");
    public static final By TOOLTIP_OPTION = By.xpath("//a[contains(text(),'Tooltip')]");


    @Test
    @Feature("Homework")
    @Story("Demo Guru 99 Test")
    @Description("Interacts with element in an iframe, closes a browser tag, raises and handling an alert and also " +
                 "dropdown")
    public void iframeTabHandlingAlertTest_Test_4() {
        logStep(OPENING_URL + URL);
        Driver.get().get(URL);

        logStep(SCROLLING_TO + IFRAME_ID);
        Scroll.to(Wait.forPresence(IFRAME_ID, _10_SECONDS, _1000_MILLIS));

        logStep("Open a new window by " + JMETER_AD_IMAGE + " under " + IFRAME_ID);
        String homeWindowHandle = Driver.get().getWindowHandle();
        Do.underIFrame(IFRAME_ID, () -> Click.on(JMETER_AD_IMAGE, _10_SECONDS, _1000_MILLIS));

        logStep("Go to the new window");
        Driver.switchToWindow(lastWindowHandle());
        Wait.forVisible(By.id("post-542"), _10_SECONDS, _1000_MILLIS);

        logStep("Close new window go back to original");
        Driver.get().close();
        Driver.switchToWindow(homeWindowHandle);

        logStep(SCROLLING_TO + SUBMIT_BUTTON);
        Scroll.to(Wait.forPresence(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS));

        logStep("Fill " + EMAIL + " in " + EMAIL_INPUT);
        WebElement emailInput = Wait.forVisible(EMAIL_INPUT, _10_SECONDS, _1000_MILLIS);
        Fill.in(emailInput, EMAIL);

        logStep("Submit email " + EMAIL);
        Click.on(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS);

        logStep("Handle alert");
        Alert alert = Driver.get().switchTo().alert();
        Assert.assertTrue(alert.getText().contains(SUCCESSFULLY),
                          SUCCESSFULLY + " is missing from the alert text: " + alert.getText());

        alert.accept();

        logStep("Opening " + SELENIUM_MENU);
        Click.on(SELENIUM_MENU, _10_SECONDS, _1000_MILLIS);

        logStep("Opening " + TOOLTIP_OPTION);
        Click.on(TOOLTIP_OPTION, _10_SECONDS, _1000_MILLIS);

        Assert.assertNotNull(Driver.get().findElement(By.id("download_now")), "Download now should be present");
    }
}
