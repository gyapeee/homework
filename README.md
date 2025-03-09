# Homework plan

## Prepare project

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

## Test implementation

- ~~Test 1~~
- ~~Test 2~~
- ~~Test 3~~
- ~~Test 4~~
- ~~Test 5 - REST api~~

## Extra improvements

- ~~Use PageObjects~~
- ~~Retry tests~~
- ~~Parallel run~~
    - ~~Verify both sequential and parallel run~
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

#     

## Questions

- is it ok filling any data as Checkout Info(firstName, lastNem, ZipCode) during checkout process?
- Is it ok adding standard_user to credentials.properties?
- What is the precise meaning of the next sentence in test 4?
  "Find the Email Submission text. Fill out the field below it and click the Submit button.". Should I use relative
  locators?

## Dev Notes

- The **_Test_X** postfix in test method's name is only for the easier identification of the test case regarding the
  documentation
  (description of homework)
