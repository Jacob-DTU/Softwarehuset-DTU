package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;

class ActivityTest {
	private Project project; 
	private Employee employee;
	private Activity activity;
	private ErrorMessageHolder error = new ErrorMessageHolder();
	@Test
	void testA() throws ProjectLeadException, OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
		project = app.newProject("testA");
		project.setProjectLead(employee);
		activity = project.newActivity(employee,"test activity", 10, 20, 30);
		assertEquals(activity.getStart(),10);
		assertEquals(activity.getEnd(),20);
		assertEquals(activity.getDuration(),30);
		assertEquals(activity.getName(),"test activity");
	}
	@Test
	void TestB() throws OperationNotAllowedException, ProjectLeadException, TooManyActivitiesException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
		project = app.newProject("testB");
		project.setProjectLead(employee);
		activity = project.newActivity(employee,"test activity", 10, 20, 30);
		employee = app.newEmployee("work");
		assertFalse(activity.contains(employee));
		activity.addEmployee(employee);
		assertTrue(activity.contains(employee));
	}
	@Test
	void TestC() throws ProjectLeadException, OperationNotAllowedException{
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		activity = project.newActivity(employee, null);
		activity.setStart(10);
		activity.setEnd(20);
		activity.setDuration(30);
		assertEquals(activity.getStart(),10);
		assertEquals(activity.getEnd(),20);
		assertEquals(activity.getDuration(),30);
	}
	@Test
	void TestD() throws OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
		project = app.newProject("TestD");
		try {
			project.newActivity(employee, "Break");
		} catch (ProjectLeadException e) {
			error.setErrorMessage(e.getMessage());
		} 
		assertEquals("Project lead is not assigned",error.getErrorMessage());
		
	}
	@Test
	void TestF() throws ProjectLeadException, OperationNotAllowedException{
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		activity = project.newActivity(employee, null);
		activity.setName("hello");
		assertEquals(activity.getName(),"hello");
	}
	@Test
	void TestG() throws ProjectLeadException, OperationNotAllowedException{
		ProjectPlannerApp app = new ProjectPlannerApp();
		Employee worker = app.newEmployee("WORK");
		employee = app.newEmployee("TEST");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		for(int i = 0; i<=40;i++) {
			activity = project.newActivity(employee, "name" + i);
			try {
				activity.addEmployee(worker);
			} catch (TooManyActivitiesException e) {
				error.setErrorMessage(e.getMessage());
			}
		}
		assertEquals(error.getErrorMessage(),"Employee is unavailable during the given timeframe");
	}
}
