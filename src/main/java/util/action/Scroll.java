package util.action;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import runner.Driver;

public class Scroll {
    public static void to(WebElement element) {
        new Actions(Driver.get()).moveToElement(element).perform();
    }
}
