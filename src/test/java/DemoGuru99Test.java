import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;
import runner.Driver;
import runner.TestBase;
import util.action.Click;
import util.action.Frame;
import util.action.Scroll;
import util.action.Wait;

import static runner.Driver.lastWindowHandle;

public class DemoGuru99Test extends TestBase {

    private static final By IFRAME_ID = By.id("a077aa5e");
    private static final By SUBMIT_BUTTON = By.id("philadelphia-field-submit");

    @Test
    public void iframeTabHandlingAlertTest_Test_4() {
        // Given
        Driver.get().get("http://demo.guru99.com/test/guru99home");

        Scroll.to(Wait.forPresence(IFRAME_ID));

        String homeWindowHandle = Driver.get().getWindowHandle();

        // When
        Frame.doUnderIFrame(IFRAME_ID, () -> Click.on(By.cssSelector("[src='Jmeter720.png']")));

        Driver.switchToWindow(lastWindowHandle());
        Wait.forVisible(By.id("post-542"));

        Driver.get().close();
        Driver.switchToWindow(homeWindowHandle);

        Scroll.to(Wait.forPresence(SUBMIT_BUTTON));

        WebElement emailInput = Wait.forVisible(By.xpath("//h3[contains(text(),'Email Submission')]"));
        RelativeLocator.with(By.xpath("//h3[contains(text(),'Email Submission')]"))
                .below(By.id("philadelphia-field-email"));

        Scroll.to(emailInput);
        // TODO Fill.in(emailInput, "user@randommail.com");

    }
}
