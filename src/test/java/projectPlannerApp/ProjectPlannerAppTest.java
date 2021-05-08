package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;
import projectPlannerCalendar.Date;

class ProjectPlannerAppTest {
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
		assertEquals(app.getProjects().get(0).getName(),project.getName());
		assertEquals(app.getProjects().get(0).getProjectNumber(),project.getProjectNumber());
	}
	@Test
	void testB() throws ProjectLeadException, OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("HELP");
		
		String inputB = "TestB";
		project = app.newProject(inputB,employee);
		assertEquals(app.getProjects().get(0),project);
		assertEquals(app.getProjects().get(0).getName(),project.getName());
		assertEquals(app.getProjects().get(0).getProjectNumber(),project.getProjectNumber());
		
	}
	@Test
	void testC() throws ProjectLeadException,OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("HELP");
		String inputB = "TestB";

		date = project.setProjectStart();;
		project = app.newProject(inputB,employee, date);
		assertEquals(app.getProjects().get(0),project);
		assertEquals(app.getProjects().get(0).getName(),project.getName());
		assertEquals(app.getProjects().get(0).getProjectNumber(),project.getProjectNumber());
		assertEquals(app.getProjects().get(0).getProjectStart(),project.getProjectStart());
		
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

/*
	private ProjectLeadException alreadyProjectLeadError = new ProjectLeadException("Project lead is already assigned");
	private ProjectLeadException noProjectLeadError = new ProjectLeadException("Project lead is not assigned");
	private ProjectLeadException notProjectLeadError = new ProjectLeadException("Only " + this.getProjectLead() + " can make a new activity");
	private OperationNotAllowedException nameAlreadyAssignedError = new OperationNotAllowedException("Activity with this name already exist");
*/