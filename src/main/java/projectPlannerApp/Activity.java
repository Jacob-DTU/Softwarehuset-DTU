package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;

public class Activity {
	
	private TooManyActivitiesException tooManyActivitiesError = new TooManyActivitiesException("Employee is unavailable during the given timeframe");
	private InvalidTimeRegistrationException invalidTimeRegistrationError = new InvalidTimeRegistrationException("Invalid time registration");

	
	public List<Employee> employees = new ArrayList<Employee>();
	public ActivityCalendar calendar;
	
	private String name;
	private int start, end, duration;
	
	public Activity(String name) {
		this.name = name;
		this.calendar = new ActivityCalendar();
	}
	
	public Activity(String name, int start, int end, int duration) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.calendar = new ActivityCalendar(start, end);
	}
		
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void addEmployee(Employee employee) throws TooManyActivitiesException {
		if (employee.currActivities.size() == 20) {
			throw tooManyActivitiesError;
		}
		else {
			employees.add(employee);
			employee.currActivities.add(this);
		}
	}
	
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double hours) throws InvalidTimeRegistrationException {
		if (hours < 0 || hours > 24) {
			throw invalidTimeRegistrationError;
		}
		else {
			TimeRegistration registration = calendar.newTimeRegistration(date, employee, hours);
			return registration;
		}
	}
	
	public TimeRegistration newTimeRegistration(PredefinedActivity predefinedActivity, Date startDate, Date endDate, Employee employee) throws InvalidTimeRegistrationException {
		TimeRegistration registration = calendar.newTimeRegistration(startDate, endDate, employee);
		calendar.addTimeRegistration(registration);
		return registration;
	}

	public boolean contains(Employee employee) {
		if (employees.contains(employee)) {
			return true;
		}
		return false;
	}
	
}
