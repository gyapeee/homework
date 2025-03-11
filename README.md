# Test framework features

Here are the features of Selenium, TestNG, Maven and Java based test framework

## Parallel test runs with TestNG

- CI workflow runs in parallel by default: https://github.com/gyapeee/homework/actions/workflows/maven.yml
- The tests can run sequentially in local if it is required by changing the ```src/test/resources/testng.xml```
    - Remove ``` parallel="methods" thread-count="6"``` from
      ```<suite name="Homework Suite" verbose="1" parallel="methods" thread-count="6">```

## Reporting with Allure

- A Quality gate is provided by scheduling test runs and Allure reports
  history:  https://gyapeee.github.io/homework/index.html
- See the Retry demonstration at the result of homework.RetryTest.sleepingTwoSecs
  ![Retry.PNG](img/Retry.PNG)
- See the screenshot demo at the result of homework.RetryTest.sleepingTwoSecs
  ![Screenshot.PNG](img/Screenshot.PNG)

## Logging with slf4j and log4j

- Each driver related action is wrapped with logs in DriverActionListener.java
- It is a solution to log every event
- This can be customized by overriding empty default methods(eg. ```default void beforeClick(WebElement element) {}```)
  of WebDriverListener.

```java

@Slf4j
public class DriverActionListener implements WebDriverListener {

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        log.info(String.format("Thread: %s | Before | Method Name: %s | Method Args: %s",
                               Thread.currentThread().getName(), method.getName(), Arrays.toString(args)));
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        log.info(String.format("Thread: %s | After  | Method Name: %s | Method Args: %s",
                               Thread.currentThread().getName(), method.getName(), Arrays.toString(args)));
    }
}
```

## Web Driver Manager

- Used for smartly managing chrome drivers
- Also runs selenium grid programmatically
  ```java 
  
  @BeforeTest(alwaysRun = true)
  public void setUpTest() {
      // Resolve driver for Selenium Grid
      WebDriverManager.chromedriver().setup();
  
      // Start Selenium Grid in standalone mode
      Main.main(new String[]{
              "standalone",
              "--port",
              "4445"
      });
  }
  ```

## Headless mode (default)

- Tests can run in headed mode by changing the ```headless=true``` to ```headless=false```

## PageObjectModel

- Test cases use Page Objects to make the framework reusable and maintainable
    - Example Page Object.
    ```java 
    public class CheckoutCompleted {
      @FindBy(css = "[data-test='checkout-complete-container']")
      private WebElement checkoutComplete;
    
      @FindBy(css = "[data-test='complete-header']")
      private WebElement header;
    
      public CheckoutCompleted() {
        PageFactory.initElements(Driver.get(), this);
        Wait.forVisible(checkoutComplete);
      }
    
      public WebElement getHeader() {
        return Wait.forVisible(header);
      }
    }
    ```

## Action utility classes

- Use actions via action util classes to make the code more readable
- Example Action

```java
public class Scroll {
    private Scroll() {
    }

    public static void to(WebElement element) {
        new Actions(Driver.get()).moveToElement(element).perform();
    }
}
```

<details>
  <summary>Homework plan</summary>

- Breaking the task down into steps can be seen below

#### Prepare project

- ~~Create github repo~~
    - ~~Create maven project~~
    - ~~Add dependencies~~
        - ~~Additional dependencies like WDM(optional)~~
    - ~~Basic tests~~
        - ~~UI~~
        - ~~API~~
        - ~~Sequential run~~
    - ~~Reporting~~
        - ~~Logging~~

#### Test implementation

- ~~Test 1~~
    - ~~Test 2~~
    - ~~Test 3~~
    - ~~Test 4~~
    - ~~Test 5 - REST api~~

#### Extra improvements

- ~~Use PageObjects~~
    - ~~Retry tests~~
    - ~~Parallel run~~
        - ~~Verify both sequential and parallel run~~
    - ~~GitHub actions~~
        - ~~build~~
        - ~~CI~~
    - Find advanced waiting mechanism
        - Each test class can set up a fluent wait for Wait actions
    - ~~Write a basic driver handler~~
    - Customize Http client
    - Use Rest Assured
    - ~~Allure reports~~
    - Stability run example
    - Refactoring if needed

#### Questions

- is it ok filling any data as Checkout Info(firstName, lastNem, ZipCode) during checkout process?
    - Is it ok adding standard_user to credentials.properties?
    - What is the precise meaning of the next sentence in test 4?
      "Find the Email Submission text. Fill out the field below it and click the Submit button.". Should I use relative
      locators?

#### Dev Notes

- The **_Test_X** postfix in test method's name is only for the easier identification of the test case regarding the
  documentation
  (description of homework)

</details>
