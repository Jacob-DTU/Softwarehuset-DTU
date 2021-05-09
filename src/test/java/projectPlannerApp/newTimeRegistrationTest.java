package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projectPlannerApp.acceptanceTest.ErrorMessageHolder;
import projectPlannerCalendar.Date;
class newTimeRegistrationTest { 
	private Date date;
	private ProjectPlannerApp app;
	private TimeRegistration time;
	private Activity activity;
	private Employee employee;
	private ErrorMessageHolder error = new ErrorMessageHolder();
	
	@BeforeEach
    void init() throws OperationNotAllowedException, ProjectLeadException {
		app = new ProjectPlannerApp();
		employee = app.newEmployee("ABCD");
		Project project = app.newProject("hello",employee);
		activity = project.newActivity(employee, "TESTA");
		date = activity.getCalendar().getDate(2021, 6, 26);
    }
	

	@Test
	void testA() throws OperationNotAllowedException {
		try {
			activity.newTimeRegistration(date, employee, -1.0);
		} catch (InvalidTimeRegistrationException e) {
			error.setErrorMessage(e.getMessage());
		}
		assertEquals("Invalid time registration",error.getErrorMessage());
	}
	@Test
	void testB() {
		try {
			activity.newTimeRegistration(date, employee, 25.0);
		} catch (InvalidTimeRegistrationException e) {
			error.setErrorMessage(e.getMessage());
		}
		assertEquals("Invalid time registration",error.getErrorMessage());
	}

	@Test
	void testC() throws InvalidTimeRegistrationException {
		time = activity.newTimeRegistration(date, employee, 5.0);
		assertEquals(time.getHours(), 5.0);
		assertEquals(time.getEmployee(), employee);
		assertEquals(time.getDate(), date);
	}

	@Test
	void testD() throws InvalidTimeRegistrationException {
		time = activity.newTimeRegistration(date, employee, 5);
		time = activity.newTimeRegistration(date, employee, 11.5);
		assertEquals(time.getHours(), 11.5);
		assertEquals(time.getEmployee(), employee);
		assertEquals(time.getDate(), date);
	}

}
