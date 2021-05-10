package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import projectPlannerApp.acceptanceTest.ErrorMessageHolder;
import org.junit.jupiter.api.BeforeEach;

class ActivityTest { //Tobias
	private Project project; 
	private Employee employee;
	private Activity activity;
	private ErrorMessageHolder error = new ErrorMessageHolder();
	private ProjectPlannerApp app = new ProjectPlannerApp();
	
	@BeforeEach
    void init() throws OperationNotAllowedException {
		app = new ProjectPlannerApp();
		employee = app.newEmployee("TEST");
    }

	
	@Test
	void testA() throws ProjectLeadException, OperationNotAllowedException {
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
		project = app.newProject("testC");
		project.setProjectLead(employee);
		activity = project.newActivity(employee, "TestF");
		activity.setName("hello");
		assertEquals(activity.getName(),"hello");
	}
	
	@Test
	void testInputRemove() throws ProjectLeadException, OperationNotAllowedException {
		Project project;
		project = app.newProject("Project", employee);
		Activity activity1 = project.newActivity(employee, "activity1", -1, -1, -1);
		Activity activity2 = project.newActivity(employee, "activity2", 0, 0, 0);
		project.removeActivity(activity1);
		assertFalse(project.contains(activity1));
		assertTrue(project.contains(activity2));		
	}

	
	@Test
	void WhiteboxTestA() throws ProjectLeadException, OperationNotAllowedException {
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
		Employee worker = app.newEmployee("ABCD");
		employee = app.newEmployee("LEAD");
		project = app.newProject("testC");
		project.setProjectLead(employee);
		activity = project.newActivity(employee, "TestC");
		activity.addEmployee(worker);
		assertEquals(project.getActivities().size(),1);
	}

}
