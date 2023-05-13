Feature: Customers

  Background: Common Steps for every Scenario
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User Enters Email as "admin@yourstore.com" and password as "admin"
    And Click to LOG IN button
    Then User can view Dashboard

    @sanity
  Scenario: Add new Customer
    When User click on customer Menu
    And Click on customer Menu Item
    And Click on Add new button
    Then User can view Add new Customer page
    When User enter customer info
    And Click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And Close Browser

  @regression
  Scenario: Search customer by email id
    When User click on customer Menu
    And Click on customer Menu Item
    And Enter customer email
    When User click on search button
    Then User should find email in the Search table
    And Close Browser

  @regression
  Scenario: Search customer by name and lastName
    When User click on customer Menu
    And Click on customer Menu Item
    And Enter customer name and lastName
    When User click on search button
    Then User should find name and lastName in the Search table
    And Close Browser