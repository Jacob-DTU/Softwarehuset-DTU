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
		activity = project.newActivity(employee, "Name");
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
		activity = project.newActivity(employee, "TestF");
		activity.setName("hello");
		assertEquals(activity.getName(),"hello");
	}
	

	
	@Test
	void WhiteboxTestA() throws ProjectLeadException, OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		Employee worker = app.newEmployee("ABCD");
		employee = app.newEmployee("LEAD");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		for(int i = 0; i<=20;i++) {
			activity = project.newActivity(employee, "name" + i);
			try {
				activity.addEmployee(worker);
			} catch (TooManyActivitiesException e) {
				error.setErrorMessage(e.getMessage());
			}
		}
		assertEquals(error.getErrorMessage(),"Employee has too many activities");
	}
	@Test
	void WhiteboxTestB() throws OperationNotAllowedException, ProjectLeadException, TooManyActivitiesException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		Employee worker = app.newEmployee("ABCD");
		employee = app.newEmployee("LEAD");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		for(int i = 0; i<=19;i++) {
			activity = project.newActivity(employee, "name" + i);
			
			
		}
		activity.addEmployee(worker);
		assertEquals(project.getActivities().size(),20);
	}
	
	@Test
	void WhiteboxTestC() throws TooManyActivitiesException, ProjectLeadException, OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		Employee worker = app.newEmployee("ABCD");
		employee = app.newEmployee("LEAD");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		activity = project.newActivity(employee, "TestC");
		activity.addEmployee(worker);
		assertEquals(project.getActivities().size(),1);
	}

}
