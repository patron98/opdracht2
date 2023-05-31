Feature: User Management
  Scenario: Adding new employee
    Given I am logged in as an admin
    When I add a new employee with name "James" "Bond"
    Then The new employee should be added with name "James Bond"

  Scenario: Searching for employee in directory
    Given I am logged in as an admin
    When I search for the employee with name "James" in Directory
    Then I should see the search results

  Scenario: Deleting an employee
    Given I am logged in as an admin
    When I delete the employee with name "James"
    Then the employee should no longer be listed
