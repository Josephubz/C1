Feature: Login Functionality

  @sanity
  Scenario: Successful log in with valid credentials
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User Enters Email as "admin@yourstore.com" and password as "admin"
    And Click to LOG IN button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on log out link
    Then Page title should be "Your store. Login"
    And Close Browser

    @regression
  Scenario Outline: Login Data Driven
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And  User Enters Email as "<email>" and password as "<password>"
    And Click to LOG IN button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User click on log out link
    Then Page title should be "Your store. Login"
    And Close Browser

    Examples:

    |email|password|
    |admin@yourstore.com|admin|
    |admin@yourstore.com123|admin123|