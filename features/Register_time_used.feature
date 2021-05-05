Feature: Register time used
Descripiton: Employees can register the time used at an activity for a timeframe
Actors: Employee

Scenario: Adding time spent on an activity
Given an activity exists 
And an employee is assigned to the activity
When the employee registers 7 hours spent at the date 01/05/2021
Then the time spent is registered at the date 01/05/2021

Scenario: Giving a wrong input when adding time spent on an activity for the current timeframe
Given an activity exists 
And an employee is assigned to the activity
When the employee registers -1 hours spent at the date 02/05/2021
Then the time registration error message "Invalid time registration" is given
And the time spent is not registered