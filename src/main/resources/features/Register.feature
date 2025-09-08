Feature: Register user
  Scenario: Successful registration
    Given I am on the home page
    When I navigate to Register
    And I enter valid registration details
    Then I should see "Your registration completed"
