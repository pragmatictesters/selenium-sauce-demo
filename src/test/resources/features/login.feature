Feature: Login to the application
  As a registered user
  I want to log in successfully
  So that I can access the homepage

  Scenario: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the homepage

  Scenario: Valid Login parameterized
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the homepage


  Scenario: Invalid Login - Incorrect Password
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "wrong_password"
    Then the user should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Invalid Login - Incorrect Username
    Given the user is on the login page
    When the user logs in with username "invalid_user" and password "secret_sauce"
    Then the user should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Login with Empty Credentials
    Given the user is on the login page
    When the user logs in with empty username and password
    Then the user should see an error message "Epic sadface: Username is required"

  Scenario: Login with Only Username
    Given the user is on the login page
    When the user logs in with username "standard_user" and empty password
    Then the user should see an error message "Epic sadface: Password is required"

  Scenario: Login with Only Password
    Given the user is on the login page
    When the user logs in with empty username and password "secret_sauce"
    Then the user should see an error message "Epic sadface: Username is required"

  Scenario Outline: Invalid Login Attempts
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see an error message "<error_message>"

    Examples:
      | username       | password       | error_message |
      | standard_user | wrong_pass     | Epic sadface: Username and password do not match any user in this service |
      | invalid_user  | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      |              | secret_sauce   | Epic sadface: Username is required |
      | standard_user |               | Epic sadface: Password is required |
      |              |                | Epic sadface: Username is required |