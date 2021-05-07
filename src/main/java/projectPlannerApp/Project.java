package projectPlannerApp;

import java.time.Year;
import java.util.List;

import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
	
	private ProjectLeadException alreadyProjectLeadError = new ProjectLeadException("Project lead is already assigned");
	private ProjectLeadException noProjectLeadError = new ProjectLeadException("Project lead is not assigned");
	private ProjectLeadException notProjectLeadError = new ProjectLeadException("Only " + this.getProjectLead() + " can make a new activity");
	private OperationNotAllowedException nameAlreadyAssignedError = new OperationNotAllowedException("Activity with this name already exist");
	
	// int year
	public HashMap<String, Activity> activities = new HashMap<String, Activity>();
	public ActivityCalendar projectCalendar = new ActivityCalendar();

	public String name;
	public String projectNumber;

	public Date projectStart;
	public Employee projectLead;	
	
	public Project(String name, int numberOfProjects) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
	}
	
	public Project(String name, int numberOfProjects, Date date) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.projectStart = date;
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.setProjectLead(projectLead);
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead, Date date) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.setProjectLead(projectLead);
		this.projectStart = date;
	}
	
	public List<Activity> getActivities() {
		return (List<Activity>) activities.values();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public Date getProjectStart() {
		return projectStart;
	}

	public void setProjectStart(Date projectStart) {
		this.projectStart = projectStart;
	}

	
	public Activity newActivity(Employee client, String name) throws ProjectLeadException, OperationNotAllowedException {
		Activity activity = createActivity(client, name, -1, -1, -1);
		
		return activity;
	}
	
	public Activity newActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException  {
		Activity activity = createActivity(client, name, start, end, duration);
		
		return activity;
	}
	
	private Activity createActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException {
		if (hasProjectLead()) {
			if (client.equals(this.getProjectLead())) {
				if (activities.containsKey(name)) {
					throw nameAlreadyAssignedError;
				} else {
					Activity activity;
					if (start == -1 || end == -1 || duration == -1) {
						activity = new Activity(name);
					} else {
						activity = new Activity(name, start, end, duration);
					}
					activities.put(name, activity);
					
					return activity;
				}
			}
			else {
				throw notProjectLeadError;
			}
		}
		else {
			throw noProjectLeadError;
		}
	}
	
	public Employee getProjectLead() {
		return projectLead;
	}
	
	public void setProjectLead(Employee employee) throws ProjectLeadException { 
		if (hasProjectLead()) {
			throw alreadyProjectLeadError;
		}
		else {
			projectLead = employee;
			employee.leadProjects.add(this);
		}		
	}
	
	public void setActivityTimeframe(Activity activity, int start, int end, int duration) throws ProjectLeadException {
		if (hasProjectLead()) {
			if (projectLead.equals(this.getProjectLead())) {
				activity.setStart(start);
				activity.setEnd(end);
				activity.setDuration(duration);
			}
			else {
				throw notProjectLeadError;
			}
		}
		else {
			throw noProjectLeadError;
		}
	}
	
	public boolean hasProjectLead() {
		if (projectLead == null) return false;
		return true; 
	}
	//setProjectStart


	public boolean contains(Activity activity) {
		if (activities.containsValue(activity)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return getProjectNumber() + " : " + getName();
	}
	
}
