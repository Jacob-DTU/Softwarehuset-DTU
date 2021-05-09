Feature: Add employee
Description: An employee is added to an activity
Actors: Employee

Scenario: Add employee successfully
	Given an activity titled "activity1" exists
	And an employee with initials "ABCD" exists
	And the employee is available
	When the employee is assigned to the activity
	Then the employee is added to the activity
	And the activity is added to the employee

Scenario: Add employee unsucessfully
	Given an activity titled "activity2" exists
	And an employee with initials "EFGH" exists
	And the employee is unavailable
	When the employee is assigned to the activity
	Then the employee is not added to the activity
	And the activity is not added to the employee
	And error message "Employee has too many activities" is shown
	
