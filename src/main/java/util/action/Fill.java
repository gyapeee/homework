package util.action;

import org.openqa.selenium.WebElement;

public class Fill {
    private Fill() {
    }

    public static void in(WebElement element, String text) {
        Wait.forClickable(element).clear();
        Wait.forClickable(element).sendKeys(text);
    }
}
