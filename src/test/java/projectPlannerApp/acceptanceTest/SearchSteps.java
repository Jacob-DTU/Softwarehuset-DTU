package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Employee;
import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectPlannerApp;

public class SearchSteps{

	private ProjectPlannerApp projectPlannerApp;
	private List<Employee> employees;
	private ErrorMessageHolder errorMessageHolder;
	
	public SearchSteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder){
	    this.projectPlannerApp = projectPlannerApp;
	    this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) throws OperationNotAllowedException {
	    projectPlannerApp.newEmployee(initials);
	}
	@When("the user searches for {string}")
	public void theUserSearchesFor(String initials) {
	    employees = projectPlannerApp.searchEmployees(initials);
	}
	
	@Then("employee with initials {string} is found")
	public void employeeWithInitialsIsFound(String initials) {
		boolean found = false;
		for (Employee existingEmployee : employees) {
			if (existingEmployee.getInitials().equals(initials)){
				found = true;
			}
		}
	    assertTrue(found);
	}
	
	@Then("no employee is found")
	public void noEmployeeIsFound() {
		assertTrue(employees.isEmpty());
	}
	
}
