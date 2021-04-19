#Feature: Edit time spent
#	Description: Employees can see time registered for a timeframe and edit it
#	Actors: Employee
#
#Scenario: Accessing time spent on activities for timeframe
#	Given the employee has registered time spent
#	When the employee enters timeframe
#	Then the hours spent is shown
#
#Scenario: Changing time spent on activity
#	Given an activity exist
#	And the employee is assigned to the activity
#	And the employee has registered time for the activity
#	When the employee changes the time spent
#	Then the time is changed