Feature: Add employee
Description: An employee is added to an activity
Actors: Employee

Scenario: Add employee successfully
	Given an activity titled "activity" exists
	And an employee with initials "ABCD" exists
	And the employee is available
	When the employee is added to the activity
	Then the employee "ABCD" is added to the activity titled "activity"
	And the activity is added to the employee

#Scenario: Add employee unsucessfully
#	Given an activity titled "activity" exists
#	And an employee with initials "ABCD" exists
#	And the employee is unavailable
#	When the employee is added to the activity
#	Then the employee "ABCD" is not added to the activity titled "activity"
#	And error-message "Employee is unavailable during the given timeframe" is shown
#	And the activity is not added to the employee
