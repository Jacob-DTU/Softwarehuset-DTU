package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;

import projectPlannerCalendar.Date;

class CreateActivityTest {
	private Employee projectLead = new Employee("ABCD");
	private Project project;
	private Date date;
	private ErrorMessageHolder error = new ErrorMessageHolder();

	@Test
	void testInputA() {
		ProjectPlannerApp app = new ProjectPlannerApp();
		try {
			app.newProject("testProject", projectLead);
		} catch (ProjectLeadException e) {
			e.printStackTrace();
		}
		private Employee employee = new Employee("EFGH");
		


	}

	@Test
	void testInputB() {
		fail("Not yet implemented");
	}


}
