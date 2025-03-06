package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public final class Driver {
    private static final ThreadLocal<Driver> DRIVER = ThreadLocal.withInitial(Driver::new);
    private WebDriver webDriver = null;

    private Driver() {
    }

    public static WebDriver get() {
        return DRIVER.get().webDriver;
    }

    public static void set(WebDriver webDriver) {
        DRIVER.get().webDriver = new EventFiringDecorator<>(new DriverActionListener()).decorate(webDriver);
    }

    public static void quit() {
        DRIVER.get().webDriver.quit();
        DRIVER.remove();
    }

    public static String lastWindowHandle() {
        int lengthOfHandles = Driver.get().getWindowHandles().size();
        return (String) Driver.get().getWindowHandles().toArray()[lengthOfHandles - 1];
    }

    public static void switchToWindow(String windowHandle) {
        Driver.get().switchTo().window(windowHandle);
        Driver.get().switchTo().defaultContent();
    }
}
