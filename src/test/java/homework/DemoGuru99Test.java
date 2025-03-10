package homework;

import io.qameta.allure.Description;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;
import util.action.*;

import static runner.Driver.lastWindowHandle;
import static util.action.Wait._1000_MILLIS;
import static util.action.Wait._10_SECONDS;

public class DemoGuru99Test extends TestBase {

    private static final By IFRAME_ID = By.id("a077aa5e");
    private static final By SUBMIT_BUTTON = By.id("philadelphia-field-submit");
    private static final String SUCCESSFULLY = "Successfully";

    @Test
    @Description("Interacts with element in an iframe, closes a browser tag, raises and handling an alert and also " +
            "dropdown")
    public void iframeTabHandlingAlertTest_Test_4() {
        Driver.get().get("http://demo.guru99.com/test/guru99home");

        Scroll.to(Wait.forPresence(IFRAME_ID, _10_SECONDS, _1000_MILLIS));

        String homeWindowHandle = Driver.get().getWindowHandle();

        Do.underIFrame(IFRAME_ID, () -> Click.on(By.cssSelector("[src='Jmeter720.png']"), _10_SECONDS, _1000_MILLIS));

        Driver.switchToWindow(lastWindowHandle());
        Wait.forVisible(By.id("post-542"), _10_SECONDS, _1000_MILLIS);

        Driver.get().close();
        Driver.switchToWindow(homeWindowHandle);

        Scroll.to(Wait.forPresence(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS));

        WebElement emailInput = Wait.forVisible(By.id("philadelphia-field-email"), _10_SECONDS, _1000_MILLIS);
        Fill.in(emailInput, "user@randommail.com");

        Click.on(SUBMIT_BUTTON, _10_SECONDS, _1000_MILLIS);

        Alert alert = Driver.get().switchTo().alert();
        Assert.assertTrue(alert.getText().contains(SUCCESSFULLY),
                SUCCESSFULLY + " is missing from the alert text: " + alert.getText());

        alert.accept();

        Click.on(By.xpath("//a[contains(text(),'Selenium')]"), _10_SECONDS, _1000_MILLIS);
        Click.on(By.xpath("//a[contains(text(),'Tooltip')]"), _10_SECONDS, _1000_MILLIS);

        Assert.assertNotNull(Driver.get().findElement(By.id("download_now")), "Download now should be present");
    }
}
