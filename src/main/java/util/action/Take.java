package util.action;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import runner.Driver;

public class Take {

    @Attachment(value = "Screenshot demonstration", type = "image/png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
    }
}
