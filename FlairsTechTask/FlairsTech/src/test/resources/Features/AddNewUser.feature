Feature: Adding a new user as an admin E2E test
  Background:
    Given I am an admin and I am logged in to the system with username and password

  Scenario: Add a new user
    When I click on the 'Admin' button
    Then I should be redirected to the Admin page
    And I get the number of records before adding a new user
    Then I click the 'Add' button
    And I enter the user's data
    Then I should be redirected to the Admin page
    And I get the number of records after adding a new user
    And Verify number of records is increased by 1
    Then I should be able to see the new user's data
    And Verify the new user's data should be correct
    And I should be able to delete the new user
    And the number of records should be decreased by 1





