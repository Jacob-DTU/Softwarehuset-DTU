package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.InvalidTimeRegistrationException;
import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectLeadException;
import projectPlannerApp.ProjectPlannerApp;
import projectPlannerApp.TimeRegistration;
import projectPlannerApp.TooManyActivitiesException;
import projectPlannerCalendar.Date;

public class TimeRegistrySteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Activity activity;
	private Employee projectLeader;
	private Employee employee;
	private Date date;
	private TimeRegistration registration;
	private double hoursSpent;
	
	public TimeRegistrySteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder) throws ProjectLeadException, OperationNotAllowedException, TooManyActivitiesException {
		this.projectPlannerApp = projectPlannerApp;
		this.errorMessageHolder = errorMessageHolder;
		this.project = projectPlannerApp.newProject("project");
		this.employee = projectPlannerApp.newEmployee("ABCD");
		this.projectLeader = projectPlannerApp.newEmployee("LEAD");
		project.setProjectLead(projectLeader);
		this.activity = project.newActivity(projectLeader, "activity");
		activity.addEmployee(employee);
	}
	
	@Given("an activity exists")
	public void anActivityExists() throws ProjectLeadException, OperationNotAllowedException {
	    assertTrue(project.contains(activity));
	}

	@Given("an employee is assigned to the activity")
	public void anEmployeeIsAssignedToTheActivity() throws TooManyActivitiesException {
		assertTrue(activity.contains(employee));
	}

	@When("the employee registers {int} hours spent at the date {int}\\/{int}\\/{int}")
	public void theEmployeeRegistersHoursSpentAtTheDate(double hours, int day, int month, int year) throws InvalidTimeRegistrationException {
		date = activity.getCalendar().getDate(year, month, day);
		try {
			registration = activity.newTimeRegistration(date, employee, hours);
		} catch(InvalidTimeRegistrationException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the time spent is registered at the date {int}\\/{int}\\/{int}")
	public void theTimeSpentIsRegisteredAtTheDate(int day, int month, int year) {
	    date = activity.getCalendar().getDate(year, month, day);
	    assertTrue(date.contains(registration));
	}
	
	@Then("the time registration error message {string} is given")
	public void theTimeRegistrationErrorMessageIsGiven(String errorMessage) {
		assertEquals(errorMessage, errorMessageHolder.getErrorMessage());
	}

	@Then("the time spent is not registered")
	public void theTimeSpentIsNotRegistered() {
	    assertFalse(date.contains(registration));
	}
	
	@Given("the employee with initials {string} has registered {int} hours spent at date {int}\\/{int}\\/{int}")
	public void theEmployeeWithInitialsHasRegisteredHoursSpentAtDate(String initials, double hours, int day, int month, int year) throws InvalidTimeRegistrationException, OperationNotAllowedException {
	    employee = projectPlannerApp.newEmployee(initials);
		date = activity.getCalendar().getDate(year, month, day);
		registration = activity.newTimeRegistration(date, employee, hours);
	}

	@When("the employee accesses the time registration")
	public void theEmployeeAccessesTheTimeRegistration() {
		registration = activity.getCalendar().getTimeRegistration(date, employee);
	}
	
	@Then("the hours spent is shown as {string}")
	public void theHoursSpentIsShownAs(String timeRegistration) {
	    assertEquals(timeRegistration, registration.toString());
	}
	
	@Given("the employee has registered {int} hours for the activity at the date {int}\\/{int}\\/{int}")
	public void theEmployeeHasRegisteredHoursForTheActivityAtTheDate(double hours, int day, int month, int year) throws InvalidTimeRegistrationException {
		date = activity.getCalendar().getDate(year, month, day);
	    hoursSpent = hours;
		registration = activity.newTimeRegistration(date, employee, hoursSpent);
	}

	@When("the employee changes the time spent to {double}")
	public void theEmployeeChangesTheTimeSpentTo(double hours) {
		registration.setHours(hours);
	}

	@Then("the time spent is changed to {double}")
	public void theTimeSpentIsChangedTo(double hours) {
	    assertTrue(registration.getHours() == hours);
	}

}
