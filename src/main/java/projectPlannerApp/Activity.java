package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;
import projectPlannerCalendar.ActivityCalendar;

public class Activity {
	
	private TooManyActivitiesException tooManyActivitiesError = new TooManyActivitiesException("Employee is unavailable during the given timeframe");
	
	private List<Employee> employees = new ArrayList<Employee>();
	private ActivityCalendar calendar;
	public boolean isPredefined;
	
	private String name;
	private int start, end, duration;
	
	public Activity(String name) {
		switch (name) {
		case "Vacation":
			this.isPredefined = true;
		default:
			this.isPredefined = false;
		}
		this.name = name;
		this.calendar = new ActivityCalendar();
	}
	
	public Activity(String name, int start, int end, int duration) {
		this.isPredefined = false;
		this.name = name;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.calendar = new ActivityCalendar();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public ActivityCalendar getCalendar() {
		return calendar;
	}
	
	public void addEmployee(Employee employee) throws TooManyActivitiesException {
		if (employee.currActivities.size() == 20) {
			throw tooManyActivitiesError;
		}
		else {
			getEmployees().add(employee);
			employee.currActivities.add(this);
		}
	}
	
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double hours) throws InvalidTimeRegistrationException {
		return calendar.newTimeRegistration(date, employee, hours);
	}
	
	public TimeRegistration newTimeRegistration(Date startDate, Date endDate, Employee employee) throws InvalidTimeRegistrationException {
		return calendar.newTimeRegistration(startDate, endDate, employee);
	}

	public boolean contains(Employee employee) {
		if (getEmployees().contains(employee)) {
			return true;
		}
		return false;
	}
	
	public void removeActivity(Employee employee){
		getEmployees().remove(employee);
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	
}
