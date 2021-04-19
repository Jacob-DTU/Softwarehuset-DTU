#Feature: Registered Time used
#Descripiton: Employees can register the time used for an activity for each timeframe
#Actors: Employee
#
#Scenario: Adding time spent on an activity for the current timeframe
#Given an activity exists 
#And the employee is assigned to the activity
#When the employee registers the time spent for the current timeframe
#Then the time spent is registered
#
#Scenario: Giving a wrong input when adding time spent on an activity for the current timeframe\\
#Given an activity exists 
#And the employee is assigned to the activity
#When the employee registers time spent for the current timeframe
#And the input is wrong
#Then the error message "Invalid time" is given
#And the time spent is not registered