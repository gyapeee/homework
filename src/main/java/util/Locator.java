package util;

import org.openqa.selenium.By;

public class Locator {
    public static By dataTest(String value) {
        return By.cssSelector("[data-test='" + value + "']");
    }
}
