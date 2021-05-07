package projectPlannerApp.acceptanceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Employee;
import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectLeadException;
import projectPlannerApp.ProjectPlannerApp;
import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;


public class ProjectSteps {

	private Project project;
	private ProjectPlannerApp projectPlannerApp;
	private Date projectStart;
	private Employee projectLead;
	
	public ProjectSteps(ProjectPlannerApp projectPlannerApp) {
		this.projectPlannerApp = projectPlannerApp;
	}
	
	@Given("no project exists")
	public void noProjectExists() {
        assertTrue(projectPlannerApp.projects.isEmpty());
	}

	@When("an employee creates a project")
	public void anEmployeeCreatesAProject() {
		this.project = projectPlannerApp.newProject("project");
	}

	@Then("the project is created")
	public void theProjectIsCreated() {
	    assertTrue(projectPlannerApp.projects.contains(project));
	}

	@Then("the project is assigned a project number")
	public void theProjectIsAssignedAProjectNumber() {
	    assertEquals("210001", project.projectNumber);
	}
	
	@Given("a project exists")
	public void aProjectExists() {
		projectPlannerApp.newProject("project");
		assertFalse(projectPlannerApp.projects.isEmpty());
	}

	@When("an employee creates a new project")
	public void anEmployeeCreatesANewProject() {
		this.project = projectPlannerApp.newProject("newProject");
	}

	@Then("the new project is created")
	public void theNewProjectIsCreated() {
		assertTrue(projectPlannerApp.projects.contains(project));
	}

	@Then("the project is assigned project number")
	public void theProjectIsAssignedProjectNumber() {
	    assertEquals("210002", project.projectNumber);
	}
	
	@When("an employee creates a project with a start time")
	public void anEmployeeCreatesAProjectWithAStartTime() {
		projectStart = new Date(2021, 1, 1);
	    project = projectPlannerApp.newProject("projectWithStartTime", 2021, 1, 1);
	}

	@Then("the start time is assigned to the project")
	public void theStartTimeIsAssignedToTheProject() {
	    assertEquals(projectStart, project.projectStart);
	}
	
	@When("an employee creates a project with a project lead assigned")
	public void anEmployeeCreatesAProjectWithAProjectLeadAssigned() throws ProjectLeadException, OperationNotAllowedException {
		this.projectLead = projectPlannerApp.newEmployee("LEAD");
	    project = projectPlannerApp.newProject("projectWithProjectLead", projectLead);
	}

	@Then("the project lead is assigned to the project")
	public void theProjectLeadIsAssignedToTheProject() {
		assertEquals(projectLead, project.getProjectLead());
	}
}
