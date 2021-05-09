package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;

import projectPlannerCalendar.Date;

class CreateActivityTest {
	private ProjectPlannerApp app = new ProjectPlannerApp();
	private Employee projectLeader;
	private ErrorMessageHolder error = new ErrorMessageHolder();

	@BeforeEach
    void init() {
		app = new ProjectPlannerApp();
		projectLeader = new Employee("ABCD");
		error = new ErrorMessageHolder();
    }

	@Test
	void testInputA() throws ProjectLeadException,OperationNotAllowedException {
		Employee employee = new Employee("EFGH");
		Project project;
		project = app.newProject("project", projectLeader);
		try {
			project.newActivity(employee, "Activity", 10, 15, 17);
		} catch (ProjectLeadException e) {
			error.setErrorMessage(e.getMessage());
		} 
		assertEquals("Only project lead can make a new activity",error.getErrorMessage());
	}

	@Test
	void testInputB() throws ProjectLeadException {
		Project project;
		project = app.newProject("project", projectLeader);
		try {
			project.newActivity(projectLeader, "Activity", 10, 15, 17);
			project.newActivity(projectLeader, "Activity", 10, 15, 17);
		} catch (OperationNotAllowedException e) {
			error.setErrorMessage(e.getMessage());
		} 
		assertEquals("Activity with this name already exist",error.getErrorMessage());
	}

	@Test
	void testInputC() throws ProjectLeadException, OperationNotAllowedException {
		Project project;
		project = app.newProject("project", projectLeader);
		Activity activity = project.newActivity(projectLeader, "activity", -1, 15, 17);
		assertTrue(project.getActivities().get("activity").getStart() == 0);
		assertTrue(project.contains(activity));
	}

	@Test
	void testInputD() throws ProjectLeadException, OperationNotAllowedException {
		Project project;
		project = app.newProject("project", projectLeader);
		Activity activity = project.newActivity(projectLeader, "activity", 10, -1, 17);
		assertTrue(project.getActivities().get("activity").getStart() == 0);
		assertTrue(project.contains(activity));
	}

	@Test
	void testInputE() throws ProjectLeadException, OperationNotAllowedException {
		Project project;
		project = app.newProject("project", projectLeader);
		Activity activity = project.newActivity(projectLeader, "activity", 10, 15, -1);
		assertTrue(project.getActivities().get("activity").getStart() == 0);
		assertTrue(project.contains(activity));
	}

	@Test
	void testInputF() throws ProjectLeadException, OperationNotAllowedException {
		Project project;
		project = app.newProject("project", projectLeader);
		Activity activity = project.newActivity(projectLeader, "activity", 10, 15, 17);
		assertTrue(project.getActivities().get("activity").getStart() == 10);
		assertTrue(project.getActivities().get("activity").getEnd() == 15);
		assertTrue(project.getActivities().get("activity").getDuration() == 17);
		assertTrue(project.contains(activity));
	}

}
