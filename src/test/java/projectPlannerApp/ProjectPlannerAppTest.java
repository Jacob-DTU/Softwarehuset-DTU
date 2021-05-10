package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;
import projectPlannerCalendar.Date;

class ProjectPlannerAppTest { //Tobias
	private Date date;
	private Project project;
	private Employee employee;
	private ErrorMessageHolder error = new ErrorMessageHolder();
	@Test
	void testA() {
		ProjectPlannerApp app = new ProjectPlannerApp();
		String inputA = "TestA";
		app.newProject(inputA);
		project = app.getProjects().get(0);
		

		assertEquals(app.getProjects().get(0),project);
		assertEquals("TestA",project.getName());
		assertEquals("210001",project.getProjectNumber());
	}
	@Test
	void testB() throws ProjectLeadException, OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("HELP");
		
		String inputB = "TestB";
		project = app.newProject(inputB,employee);
		
		assertEquals(app.getProjects().get(0),project);
		assertEquals("TestB",project.getName());
		assertEquals("210001",project.getProjectNumber());
		
	}
	@Test
	void testC() throws ProjectLeadException,OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("HELP");
		String inputC = "TestC";
		project = app.newProject(inputC,employee);
		project.setProjectStart(date);;
		
		assertEquals(app.getProjects().get(0),project);
		assertEquals("TestC",project.getName());
		assertEquals("210001",project.getProjectNumber());
		
		assertEquals(date,project.getProjectStart());
		assertEquals(employee,project.getProjectLead());
		
	}
	@Test
	void testD() throws OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("HELP");
		String inputB = "TestB";
		try {	
				app.newProject(inputB,employee);
				employee = app.newEmployee("TEST");
				app.getProjects().get(0).setProjectLead(employee);
		}catch(ProjectLeadException e){
			error.setErrorMessage(e.getMessage());
		}
	
		assertEquals("Project lead is already assigned", error.getErrorMessage());
	}
	
}