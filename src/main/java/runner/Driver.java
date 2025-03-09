package runner;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Slf4j
public final class Driver {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver get() {
        return DRIVER.get();
    }

    public static void set(WebDriver webDriver) {
        log.info(Thread.currentThread().getName() + " thread(" + Thread.currentThread().getId() + ") is setting " +
                "the driver " + webDriver);
        DRIVER.set(new EventFiringDecorator<>(new DriverActionListener()).decorate(webDriver));
    }

    public static void quit() {
        if (DRIVER.get() != null) {
            log.info(Thread.currentThread().getName() + " thread(" + Thread.currentThread().getId() + ") is quitting " +
                    "the driver " + DRIVER.get());
            DRIVER.get().quit();
            DRIVER.remove();
        }
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
