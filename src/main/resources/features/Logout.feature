Feature: Logout
  Scenario: Valid logout
    Given I am logged in
    When I click logout
    Then I should be logged out
