package util.action;

import org.openqa.selenium.WebElement;

public class Click {
    public static void on(WebElement element) {
        Wait.forVisible(element).click();
    }
}
