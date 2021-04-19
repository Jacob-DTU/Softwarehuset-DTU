#Feature: Choose project lead
#	Description: A project lead is assigned to a project
#	Actors: Employee
#
#Scenario: Choose a project lead
#	Given an employee is available
#	And a project exists without a project lead
#	When an available employee is assigned as manager
#	Then a project lead is assigned to the project
#	And the employee is the project lead for the project
#
#Scenario: Choose a project lead for a project with a project manger
#	Given an employee is available
#	And a project exists with a project lead
#	When an available employee is assigned as project lead
#	Then the error message "Project lead is already assigned" is given
#	And the employee is not assigned as project lead