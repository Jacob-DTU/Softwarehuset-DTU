Feature: Create project
	Description: An employee creates a new project
	Actors: Employee

Scenario: Create a project without project lead
	Given no project exists
	When an employee creates a project
	Then the project is created
	And the project is assigned a project number
	
Scenario: Create a new project
	Given a project exists
	When an employee creates a new project
	Then the new project is created
	And the project is assigned project number

Scenario: Create a project with a project lead assigned
	Given no project exists
	When an employee creates a project with a project lead assigned
	Then the project is created
	And the project lead is assigned to the project

Scenario: Create a project with a start time
	Given no project exists
	When an employee creates a project with a start time
	Then the project is created
	And the start time is assigned to the project
