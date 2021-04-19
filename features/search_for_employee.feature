#Feature: Search for employees
#Description: Employee searches for an employee
#Actors: Employee
#
#Scenario: Search for an employee
#Given there is an employee with initials "ABCD"
#When the user searches for "ABCD"
#Then employee with initials "ABCD" is found
#
#Scenario: Search for a substring of employee
#Given there is an employee with initials "ABCD"
#And there is an employee with initials "ABEF"
#When the user searches for "AB"
#Then employee with initials "ABCD"\space is found
#And employee with initials "ABEF"\space is found
#
#Scenario: No employee with such initials exists
#Given there is an employee with initials "ABCD"
#When the user searches for "EFGH"
#Then no employee is found
#And the error message "No employee found with " is given