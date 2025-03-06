package util.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Click {
    private Click() {
    }

    public static void on(WebElement element) {
        Wait.forClickable(element).click();
    }

    public static void on(By element) {
        Wait.forClickable(element).click();
    }

    public static void on(By element, int seconds, int millis) {
        Wait.forClickable(element, seconds, millis).click();
    }
}
