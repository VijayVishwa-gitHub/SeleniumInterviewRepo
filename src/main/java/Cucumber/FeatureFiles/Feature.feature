Feature: Testing DataTable concept

  @Regression
  Scenario: Login into application
    Given user clicks on the login link
    When user enters valid username and password
      | username               | password    |
      | vijayvishwa@gmail.com  | Tester@1234 |
      | vijayvishwa1@gmail.com | Tester@0987 |
    Then user logs into the application
    

  @smoke
  Scenario Outline: Login into application using Scenario Outline
    Given user clicks on the login link
    When user enters valid credentials "<username>" and "<password>"
    Then user logs into the application

    Examples:
      | username               | password    |
      | vijayvishwa@gmail.com  | Tester@1234 |
      | vijayvishwa1@gmail.com | Tester@0987 |
