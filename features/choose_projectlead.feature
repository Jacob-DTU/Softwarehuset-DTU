Feature: Choose project lead
	Description: A project lead is assigned to a project
	Actors: Employee

Scenario: Choose a project lead
	Given an employee with initials "ABCD" is available
	And a project with name "project1" exists without a project lead
	When the employee is assigned as project lead
	Then the project has a project lead
	And the employee is the project lead for the project

Scenario: Choose a project lead for a project with a project manger
	Given an employee with initials "EFGH" is available
	And a project with name "project2" exists with a project lead
	When the employee is assigned as project lead
	Then the project lead error message "Project lead is already assigned" is given
	And the employee is not assigned as project lead