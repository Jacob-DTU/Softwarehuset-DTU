#Feature: predefined_activities
#	Description: Employee's can select predefined activities for their own timeframe
#	Actors: Employee
#
#Scenario: Joining a predefined activity
#	Given a predefined activity exists
#	When an employee selects a predefined activity with a given timeframe.
#	Then other activitys are unavaible for this timeframe.
#
#Scenario: Removing predefined activity
#	Given an employee with a predefined activity with a timeframe
#	When a user removes a predefined activity.
#	Then other activitys become available in the given timeframe.