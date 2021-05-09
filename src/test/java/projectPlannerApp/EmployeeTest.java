package projectPlannerApp;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;

class EmployeeTest {
	private Employee employee;
	private ErrorMessageHolder error = new ErrorMessageHolder();
	private Project project;
	@Test
	void testA() throws OperationNotAllowedException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("test");
		assertEquals("test",employee.getInitials());
	}
	@Test
	void testB()  {
		ProjectPlannerApp app = new ProjectPlannerApp();
		try {
			app.newEmployee("test");
			app.newEmployee("test");
		}catch(OperationNotAllowedException e) {
			error.setErrorMessage(e.getMessage());
		}
		assertEquals("An employee with these initials already exists", error.getErrorMessage());
	}
	@Test
	void testC() throws ProjectLeadException, OperationNotAllowedException  {
		ProjectPlannerApp app = new ProjectPlannerApp();
		try {
			employee = app.newEmployee("test");
			project =app.newProject("testc");
			project.setProjectLead(employee);
			for(int i = 0; i<=21;i++) {
				project.newActivity(employee, "activity " + i);
			}
			employee = app.newEmployee("Work");
			for(Activity a :project.getActivities().values() ) {
				a.addEmployee(employee);
			}
		}catch(TooManyActivitiesException e) {
			error.setErrorMessage(e.getMessage());
		}
		assertEquals("Employee is unavailable during the given timeframe", 
				error.getErrorMessage());
		
		
		assertFalse(employee.isAvailable());
	}
	@Test
	void testD() throws ProjectLeadException, OperationNotAllowedException  {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("test");
		employee.setInitials("newD");
		assertEquals("newD",employee.getInitials());
}
	@Test
	void testE() throws OperationNotAllowedException, ProjectLeadException {
		ProjectPlannerApp app = new ProjectPlannerApp();
		employee = app.newEmployee("test");
		app.newProject("testE", employee);
		assertEquals(employee.leadProjects,app.getEmployee("test").leadProjects);
	}
}
