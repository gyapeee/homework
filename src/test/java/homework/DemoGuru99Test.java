package homework;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.DemoGuru99Page;
import runner.Driver;
import runner.TestBase;
import util.action.Wait;

import static runner.Driver.lastWindowHandle;
import static util.action.Wait._1000_MILLIS;
import static util.action.Wait._10_SECONDS;

public class DemoGuru99Test extends TestBase {

    private static final String URL = "http://demo.guru99.com/test/guru99home";
    private static final String SUCCESSFULLY = "Successfully";
    public static final String EMAIL = "user@randommail.com";

    @Test
    @Feature("Homework")
    @Story("Demo Guru 99 Test")
    @Description(
            "Interacts with elements in an iframe, closes a browser tab, raises and handles an alert, and interacts with a dropdown")
    public void iframeTabHandlingAlertTest_Test_4() {
        DemoGuru99Page demoGuru99Page = new DemoGuru99Page();

        logStep("Opening URL: " + URL);
        Driver.get().get(URL);

        logStep("Scrolling to iframe and interacting with an ad");
        demoGuru99Page.interactWithIframeAd();

        logStep("Switching to new window");
        String homeWindowHandle = Driver.get().getWindowHandle();
        Driver.switchToWindow(lastWindowHandle());
        Wait.forVisible(demoGuru99Page.getPostElement(), _10_SECONDS, _1000_MILLIS);

        logStep("Closing new window and returning to original");
        Driver.get().close();
        Driver.switchToWindow(homeWindowHandle);

        logStep("Filling email input and submitting");
        demoGuru99Page.fillAndSubmitEmail(EMAIL);

        logStep("Handling alert");
        Alert alert = Driver.get().switchTo().alert();
        Assert.assertTrue(alert.getText().contains(SUCCESSFULLY),
                          SUCCESSFULLY + " is missing from the alert text: " + alert.getText());
        alert.accept();

        logStep("Navigating to Selenium Tooltip section");
        demoGuru99Page.navigateToSeleniumTooltip();

        Assert.assertNotNull(demoGuru99Page.getDownloadNowElement(), "Download now should be present");
    }
}
