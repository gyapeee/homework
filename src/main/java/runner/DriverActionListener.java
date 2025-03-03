package runner;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class DriverActionListener implements WebDriverListener {

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        log.info(String.format("Thread: %s | Before | Method Name: %s | Method Args: %s",
                Thread.currentThread().getName(),
                method.getName(),
                Arrays.toString(args)));
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        log.info(String.format("Thread: %s | After  | Method Name: %s | Method Args: %s",
                Thread.currentThread().getName(),
                method.getName(),
                Arrays.toString(args)));
    }
}
