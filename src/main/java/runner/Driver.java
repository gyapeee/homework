package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public final class Driver {
    private static final ThreadLocal<Driver> driver = ThreadLocal.withInitial(Driver::new);
    private WebDriver webDriver = null;

    private Driver() {
    }

    public static WebDriver get() {
        return driver.get().webDriver;
    }

    public static void set(WebDriver webDriver) {
        driver.get().webDriver = new EventFiringDecorator<>(new DriverActionListener()).decorate(webDriver);
    }

    public static void quit() {
        driver.get().webDriver.quit();
        driver.remove();
    }
}
