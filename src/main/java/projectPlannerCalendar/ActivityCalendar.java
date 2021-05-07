package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

public class ActivityCalendar extends ProjectPlannerCalendar {
		
	public ActivityCalendar() {
		super();
	}
	
	public ActivityCalendar(int startWeek, int endWeek) {
		super();
	}
		
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double hours) {
		TimeRegistration registration = new TimeRegistration(date, employee, hours);
		
		if (timeRegistrations.size() > 1) {
			int index = sortInsert(timeRegistrations.size() / 2, registration);
			timeRegistrations.add(index, registration);
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}

	
}
