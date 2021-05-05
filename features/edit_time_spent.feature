Feature: Edit time spent
	Description: Employees can see time registered for a timeframe and edit it
	Actors: Employee

Scenario: Accessing time spent on activities for timeframe
	Given the employee with initials "TEST" has registered 5 hours spent at date 01/05/2021
	When the employee accesses the time registration
	Then the hours spent is shown as "01/05/2021	TEST	5.0"

Scenario: Changing time spent on activity
	Given an activity exists
	And an employee is assigned to the activity
	And the employee has registered 6 hours for the activity at the date 05/05/2021
	When the employee changes the time spent to 6.5
	Then the time spent is changed to 6.5