#
#Feature: plan_activities
#Description: project lead has the power to make new activites and add people to it.
#Actors: project lead
#
#
#Scenario: Making a new activity
#Given that a project exists
#And the project has a project lead
#When the project lead makes a new activity
#Then the activity is created
#And is assigned to the project
#
#Scenario: Making a new activity with the same name as another activity
#Given that a project exists
#And the project has a project lead
#And the an activity is assigned to the project
#When the project lead makes a new activity with the same name
#Then the error message "Activity with 'Name' already exist" is given
#And no new activity is created
#
#
#Scenario: Choosing timeframe
#Given an activity has no timeframe
#When a new activity is made
#Then add a start time and end time.
#
#
#Scenario: Add employees 
#Given a activty with a timeframe exits.
#Then add a employee to this activity.
#When employee isn't already in 20 other activty's.
#
#Scenario: Make predefined activities
#Given an activity exists
#When an activity is made predefined
#Then lose timeframe 
#And become accessable for the employees to join