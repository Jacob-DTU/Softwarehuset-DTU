package projectPlannerApp.acceptanceTest;

import projectPlannerApp.Employee;
import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectLeadException;
import projectPlannerApp.ProjectPlannerApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProjectleadSteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private Project project;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;
	
	public ProjectleadSteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder) {
		this.projectPlannerApp = projectPlannerApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("an employee with initials {string} is available")
	public void anEmployeeWithInitialsIsAvailable(String initials) throws OperationNotAllowedException {
	    employee = projectPlannerApp.newEmployee(initials);
	    assertTrue(employee.isAvailable());
	}

	@Given("a project with name {string} exists without a project lead")
	public void aProjectWithNameExistsWithoutAProjectLead(String projectName) {
		project = projectPlannerApp.newProject(projectName);
	    assertFalse(project.hasProjectLead());
	}

	@When("the employee is assigned as project lead")
	public void theEmployeeIsAssignedAsProjectLead() throws ProjectLeadException {
		try {
			project.setProjectLead(employee);
		} catch(ProjectLeadException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project has a project lead")
	public void theProjectHasAProjectLead() {
	    assertTrue(project.hasProjectLead());
	}

	@Then("the employee is the project lead for the project")
	public void theEmployeeIsTheProjectLeadForTheProject() {
	    employee.leadProjects.contains(project);
	}
	
	@Given("a project with name {string} exists with a project lead")
	public void aProjectWithNameExistsWithAProjectLead(String projectName) throws ProjectLeadException, OperationNotAllowedException {
		project = projectPlannerApp.newProject(projectName, projectPlannerApp.newEmployee("IJKL"));
	    assertTrue(project.hasProjectLead());
	}

	@Then("the project lead error message {string} is given")
	public void theProjectLeadErrorMessageIsGiven(String errorMessage) {
		assertEquals(errorMessage, errorMessageHolder.getErrorMessage());
	}

	@Then("the employee is not assigned as project lead")
	public void theEmployeeIsNotAssignedAsProjectLead() {
	    assertNotEquals(employee, project.getProjectLead());
	}
	
}
