package projectPlannerApp.acceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.Project;
import projectPlannerApp.ProjectPlannerApp;

public class PredefinedActivitySteps {
	
	private ProjectPlannerApp projectPlannerApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Employee projectLead;
	private Activity activity;
	
	public PredefinedActivitySteps(ProjectPlannerApp projectPlannerApp, ErrorMessageHolder errorMessageHolder) {
		this.projectPlannerApp = projectPlannerApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("a predefined activity exists")
	public void aPredefinedActivityExists() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("an employee selects a predefined activity with a given timeframe.")
	public void anEmployeeSelectsAPredefinedActivityWithAGivenTimeframe() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("other activitys are unavaible for this timeframe.")
	public void otherActivitysAreUnavaibleForThisTimeframe() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
