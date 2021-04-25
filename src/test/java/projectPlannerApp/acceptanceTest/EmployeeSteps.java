package projectPlannerApp.acceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.PendingException;


public class EmployeeSteps {
	@Given("an activity titled {string} exists")
	public void anActivityTitledExists(String activity) {
		
	}

	@Given("an employee with initials {string} exists")
	public void anEmployeeWithInitialsExists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("the employee is available")
	public void theEmployeeIsAvailable() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the employee is added to the activity")
	public void theEmployeeIsAddedToTheActivity() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the employee {string} is added to the activity titled {string}")
	public void theEmployeeIsAddedToTheActivityTitled(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the activity is added to the employee")
	public void theActivityIsAddedToTheEmployee() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
