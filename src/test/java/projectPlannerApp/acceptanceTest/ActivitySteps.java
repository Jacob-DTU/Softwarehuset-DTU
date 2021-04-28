package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectLeadException;
import projectPlannerApp.ProjectPlannerApp;

public class ActivitySteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private Project project;
	private Employee projectLead;
	private Activity activity;
	
	// Vi har ændret lidt i feature filen
	@Given("that a project with a project lead exists")
	public void thatAProjectWithAProjectLeadExists() throws ProjectLeadException {
		projectLead = projectPlannerApp.newEmployee("ABCD");
	    project = projectPlannerApp.newProject("project1", projectLead);
	}

	@When("the project lead makes a new activity")
	public void theProjectLeadMakesANewActivity() throws ProjectLeadException {
	    activity = project.newActivity(projectLead, "activity", 1, 2, 20);
	}

	@Then("the activity is assigned to the project")
	public void theActivityIsAssignedToTheProject() {
		assertTrue(project.activities.contains(activity));
	}

	
}
