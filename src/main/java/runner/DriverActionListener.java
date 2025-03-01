package runner;


import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DriverActionListener implements WebDriverListener {
    private static final Logger log = LoggerFactory.getLogger(DriverActionListener.class);

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        log.info(String.format("Thread: %s | Method Name: %s | Method Args: %s",
                Thread.currentThread().getName(), method.getName(), Arrays.toString(args)));
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        log.info(String.format("Thread: %s | Method Name: %s | Method Args: %s",
                Thread.currentThread().getName(), method.getName(), Arrays.toString(args)));
    }
}
