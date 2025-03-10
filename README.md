# Test framework features

Here are the features of the test framework of the homework

## Parallel test runs with TestNG

- CI workflow runs in parallel by default: https://github.com/gyapeee/homework/actions/workflows/maven.yml
- The tests can run sequentially in local if it is required by changing the ```src/test/resources/testng.xml```

## Reporting with Allure

- A Quality gate is provided by scheduling test runs and Allure reports
  history:  https://gyapeee.github.io/homework/index.html

## Homework plan

- Breaking the task down into steps can be seen below

### Prepare project

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

### Test implementation

- ~~Test 1~~
- ~~Test 2~~
- ~~Test 3~~
- ~~Test 4~~
- ~~Test 5 - REST api~~

### Extra improvements

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

### Questions

- is it ok filling any data as Checkout Info(firstName, lastNem, ZipCode) during checkout process?
- Is it ok adding standard_user to credentials.properties?
- What is the precise meaning of the next sentence in test 4?
  "Find the Email Submission text. Fill out the field below it and click the Submit button.". Should I use relative
  locators?

### Dev Notes

- The **_Test_X** postfix in test method's name is only for the easier identification of the test case regarding the
  documentation
  (description of homework)
