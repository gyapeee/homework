package util.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runner.Driver;

public class Frame {
    private Frame() {
    }

    public static void doUnderIFrame(By fameLocator, Runnable action) {
        WebElement frame = Driver.get().findElement(fameLocator);
        Driver.get().switchTo().frame(Wait.forVisible(frame));
        action.run();
        Driver.get().switchTo().defaultContent();
    }
}
