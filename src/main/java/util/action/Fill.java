package util.action;

import org.openqa.selenium.WebElement;

public class Fill {
    public static void in(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
