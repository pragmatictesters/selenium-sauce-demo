Feature: Login to the application


  Scenario: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the homepage