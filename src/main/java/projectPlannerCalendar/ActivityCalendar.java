package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

public class ActivityCalendar extends ProjectPlannerCalendar {
	
	public List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();
	
	public ActivityCalendar() {
		super();
	}
	
	public ActivityCalendar(int startWeek, int endWeek) {
		super();
	}
		
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double time) {
		TimeRegistration registration = new TimeRegistration(date, employee, time);
		
		if (timeRegistrations.size() > 1) {
			int index = sortInsert(timeRegistrations.size() / 2, registration);
			timeRegistrations.add(index, registration);
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}

	private int sortInsert(int index, TimeRegistration registration) {
		Date currDate = timeRegistrations.get(index).date;
		Date prevDate = timeRegistrations.get(index - 1).date;
		Date nextDate = timeRegistrations.get(index + 1).date;
		if (prevDate.isLEQThan(registration.date) && nextDate.isGEQThan(registration.date)) {
			return index;
		}
		else if (currDate.isLessThan(registration.date)) {
			index += index / 2;
		}
		else if (currDate.isGreaterThan(registration.date)) {
			index /= 2;
		}
		return sortInsert(index, registration);
	}

	public void addTimeRegistration(TimeRegistration registration) {
		timeRegistrations.add(registration);
	}
}
