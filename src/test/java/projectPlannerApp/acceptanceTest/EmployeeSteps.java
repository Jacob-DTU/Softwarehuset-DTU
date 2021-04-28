package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectPlannerApp;
import projectPlannerApp.TooManyActivitiesException;


public class EmployeeSteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private Project project;
	private Activity activity;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;
	
	public EmployeeSteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder) {
		this.projectPlannerApp = projectPlannerApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("an activity titled {string} exists")
	public void anActivityTitledExists(String activityName) {
		project = projectPlannerApp.newProject(activityName);
		activity = project.newActivity(activityName, 1, 2, 10);
	}

	@Given("an employee with initials {string} exists")
	public void anEmployeeWithInitialsExists(String initials) {
		employee = projectPlannerApp.newEmployee(initials);
	}

	@Given("the employee is available")
	public void theEmployeeIsAvailable() {
	    assertTrue(employee.isAvailable());
	}

	@When("the employee is assigned to the activity")
	public void theEmployeeIsAssignedToTheActivity() throws TooManyActivitiesException {
	    try {
			activity.addEmployee(employee);
	    } catch(TooManyActivitiesException e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the employee is added to the activity")
	public void theEmployeeIsAddedToTheActivity() {
		
	    assertTrue(activity.employees.contains(employee));
	}

	@Then("the activity is added to the employee")
	public void theActivityIsAddedToTheEmployee() {
		assertTrue(employee.currActivities.contains(activity));
	}
	
	@Given("the employee is unavailable")
	public void theEmployeeIsUnavailable() throws TooManyActivitiesException {
	    for (int i=0; i<20; i++) {
	    	Activity tmpActivity = project.newActivity(Integer.toString(i), 1, 2, 10);
	    	tmpActivity.addEmployee(employee);
	    }
	    
	    assertFalse(employee.isAvailable());
	}

	@Then("the employee is not added to the activity")
	public void theEmployeeIsNotAddedToTheActivity() {
	    assertFalse(activity.employees.contains(employee));
	}

	@Then("the activity is not added to the employee")
	public void theActivityIsNotAddedToTheEmployee() {
		assertFalse(employee.currActivities.contains(activity));
	}
	
	@Then("error-message {string} is shown")
	public void errorMessageIsShown(String errorMessage) {
		assertEquals(errorMessage, errorMessageHolder.getErrorMessage());
	}
	
}
