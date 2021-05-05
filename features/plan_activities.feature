Feature: plan_activities
	Description: project lead has the power to make new activites and add people to it.
	Actors: project lead


Scenario: Making a new activity
	Given that a project with a project lead exists
	When the project lead makes a new activity
	Then the activity is assigned to the project

Scenario: Making a new activity with the same name as another activity
	Given that a project with a project lead exists
	And an activity with name "activity" is assigned to the project
	When the project lead makes a new activity with the name "activity"
	Then the activity error message "Activity with this name already exist" is given
	And no new activity is created

Scenario: Choosing timeframe
	Given an activity has no timeframe
	When the project lead adds a start time 1, end time 4 and duration 20 to the activity
	Then a timeframe is added to the activity
