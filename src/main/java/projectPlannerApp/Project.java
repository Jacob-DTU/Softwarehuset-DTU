package projectPlannerApp;

import java.time.Year;
import java.util.List;

import projectPlannerCalendar.ActivityCalendar;
import projectPlannerCalendar.Date;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Project {
	
	private ProjectLeadException alreadyProjectLeadError = new ProjectLeadException("Project lead is already assigned");
	private ProjectLeadException noProjectLeadError = new ProjectLeadException("Project lead is not assigned");
	private ProjectLeadException notProjectLeadError = new ProjectLeadException("Only project lead can make a new activity");
	private OperationNotAllowedException nameAlreadyAssignedError = new OperationNotAllowedException("Activity with this name already exist");
	private OperationNotAllowedException noSuchNameError = new OperationNotAllowedException("Activity with this name does not exist");
	
	private HashMap<String, Activity> activities = new HashMap<String, Activity>();

	private String name;
	private String projectNumber;

	private Date projectStart;
	private Employee projectLead;

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
		this.projectLead = projectLead;
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead, Date date) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.projectLead = projectLead;
		this.projectStart = date;
	}
	
	public HashMap<String, Activity> getActivities() {
		return activities;
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

	public Date getProjectStart() {
		return projectStart;
	}

	public void setProjectStart(Date projectStart) {
		this.projectStart = projectStart;
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
			employee.getProjects().add(this);
		}		
	}
	
	public Activity newActivity(Employee client, String name) throws ProjectLeadException, OperationNotAllowedException {
		Activity activity = createActivity(client, name, -1, -1, -1);
		
		return activity;
	}
	
	public Activity newActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException  {
		Activity activity = createActivity(client, name, start, end, duration);
		
		return activity;
	}
	//Startvariable ProjectLead
	private Activity createActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException {
		if (hasProjectLead()) {
			if (client.equals(projectLead)) {//1
				if (activities.containsKey(name)) {//2
					throw nameAlreadyAssignedError;
				} else {
					Activity activity;
					if (start == -1 || end == -1 || duration == -1) {//3
						activity = new Activity(name);
					} else {
						activity = new Activity(name, start, end, duration);
					}
					activity.setAssociatedProject(this);
					activities.put(name, activity);
					
					return activity;
				}
			}
			else {
			System.out.println(client.getInitials());
			System.out.println(projectLead.getInitials());
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
	


	public boolean contains(Activity activity) {
		if (activities.containsValue(activity)) {
			return true;
		}
		return false;
	}
	
	public void removeActivity(Activity activity) throws OperationNotAllowedException{
		if (this.contains(activity)) {
			activities.remove(activity.getName());
		}
		else {
			throw noSuchNameError;
		}	
	}
	
	public String toString() {
		String startText = "";
		String leadText = "";
		startText = getProjectStart() == null? "  ProjectStart : " + getProjectStart().toString(): "";
		leadText = getProjectLead()== null? " ProjectLead : " + getProjectLead().toString() : "";
		return getProjectNumber() + " : " + getName() + startText + leadText;
	}

}

