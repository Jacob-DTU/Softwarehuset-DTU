package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;
import projectPlannerCalendar.ActivityCalendar;

public class Activity {
	
	private TooManyActivitiesException tooManyActivitiesError = new TooManyActivitiesException("Employee has too many activities");
	private ProjectLeadException notProjectLeadError = new ProjectLeadException("Only project lead can make a new activity");

	
	private List<Employee> employees = new ArrayList<Employee>();
	private ActivityCalendar calendar;
	private Project associatedProject;
	public boolean isPredefined;
	
	private String name;
	private int start, end, duration;
	
	public Activity(String name) {
		switch (name) {
			case "Vacation":
				this.isPredefined = true;
				break;
			default:
				this.isPredefined = false;
		}
		this.name = name;
		this.calendar = new ActivityCalendar();
		calendar.setActivity(this);
	}
	
	public Activity(String name, int start, int end, int duration) {
		this.isPredefined = false;
		this.name = name;
		this.calendar = new ActivityCalendar();
		calendar.setActivity(this);
		setStart(start);
		setEnd(end);
		this.duration = duration;
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
		calendar.setStart(calendar.getDate(start));
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
	
	public void setAssociatedProject(Project project) {
		this.associatedProject = project;
	}
	
	public void setTimeframe(Employee client, int start, int end, int duration) throws ProjectLeadException {
		if (client.equals(associatedProject.getProjectLead())) {
			setStart(start);
			setEnd(end);
			setDuration(duration);
		}
		else {
			throw notProjectLeadError;
		}
	}

	public void addEmployee(Employee employee) throws TooManyActivitiesException {
		assert !employee.equals(null) && employee.isAvailable(): "Precondition";
		if (employee.getActivities().size() == 20) { //1
			throw tooManyActivitiesError;
		}
		else {
			getEmployees().add(employee);
			employee.getActivities().add(this);
		}
		assert employee.getActivities().contains(this): "Postcondition";
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
	
	public void removeEmployee(Employee employee){
		getEmployees().remove(employee);
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public String toString(){
		return getName() + " : " + getStart() + " : " + getEnd() + " : " + getDuration();
	}
}
