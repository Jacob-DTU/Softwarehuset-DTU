package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectLeadException;
import projectPlannerApp.ProjectPlannerApp;

public class ActivitySteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Employee projectLead;
	private Activity activity;
	
	public ActivitySteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder) throws OperationNotAllowedException {
		this.projectPlannerApp = projectPlannerApp;
		this.errorMessageHolder = errorMessageHolder;
		this.projectLead = projectPlannerApp.newEmployee("LEAD");
	}
	
	// Vi har ændret lidt i feature filen
	@Given("that a project with a project lead exists")
	public void thatAProjectWithAProjectLeadExists() throws ProjectLeadException {
	    project = projectPlannerApp.newProject("project1", projectLead);
	}

	@When("the project lead makes a new activity")
	public void theProjectLeadMakesANewActivity() throws ProjectLeadException, OperationNotAllowedException {
	    activity = project.newActivity(projectLead, "testActivity");
	}

	@Then("the activity is assigned to the project")
	public void theActivityIsAssignedToTheProject() {
		assertTrue(project.contains(activity));
	}

	@Given("an activity with name {string} is assigned to the project")
	public void anActivityWithNameIsAssignedToTheProject(String name) throws ProjectLeadException, OperationNotAllowedException {
		activity = project.newActivity(projectLead, name);
	}

	@When("the project lead makes a new activity with the name {string}")
	public void theProjectLeadMakesANewActivityWithTheName(String name) throws ProjectLeadException, OperationNotAllowedException {
		try {
			project.newActivity(projectLead, name);
		} catch(OperationNotAllowedException e){
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the activity error message {string} is given")
	public void theActivityErrorMessageIsGiven(String errorMessage) {
		assertEquals(errorMessage, errorMessageHolder.getErrorMessage());
	}
	
	@Then("no new activity is created")
	public void noNewActivityIsCreated() {
	    assert(activity.equals(project.activities.get("activity")));
	}
	
	@Given("an activity has no timeframe")
	public void anActivityHasNoTimeframe() throws ProjectLeadException, OperationNotAllowedException {
		project = projectPlannerApp.newProject("project", projectLead);
		activity = project.newActivity(projectLead, "activity1");
	}

	@When("the project lead adds a start time {int}, end time {int} and duration {int} to the activity")
	public void theProjectLeadAddsAStartTimeEndTimeAndDurationToTheActivity(int start, int end, int duration) throws ProjectLeadException {
	    project.setActivityTimeframe(activity, start, end, duration);
	}

	@Then("a timeframe is added to the activity")
	public void aTimeframeIsAddedToTheActivity() {
	    assertNotEquals(activity.getStart(), -1);
	    assertNotEquals(activity.getEnd(), -1);
	    assertNotEquals(activity.getDuration(), -1);
	}
}
