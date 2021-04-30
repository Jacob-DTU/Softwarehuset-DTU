package projectPlannerApp;

import java.time.Year;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Project {
	
	public HashMap<String, Activity> activities = new HashMap<String, Activity>();
	private ProjectLeadException alreadyProjectLeadError = new ProjectLeadException("Project lead is already assigned");
	private ProjectLeadException noProjectLeadError = new ProjectLeadException("Project lead is not assigned");
	private ProjectLeadException notProjectLeadError = new ProjectLeadException("Only " + this.getProjectLead() + " can make a new activity");
	private OperationNotAllowedException nameAlreadyAssignedError = new OperationNotAllowedException("Activity with this name already exist");
	// int year
	public String name;
	public String projectNumber;
	public Calendar projectStart;
	public Employee projectLead;	
	
	public Project(String name, int numberOfProjects) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		
	}
	
	public Project(String name, int numberOfProjects, Calendar projectStart) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.projectStart = projectStart;
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.setProjectLead(projectLead);
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead,  Calendar projectStart) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.setProjectLead(projectLead);
		this.projectStart = projectStart;
	}
	
	public Activity newActivity(Employee projectLeader, String name) throws ProjectLeadException, OperationNotAllowedException {
		Activity activity = createActivity(projectLeader, name, -1, -1, -1);
		
		return activity;
	}
	
	public Activity newActivity(Employee projectLeader, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException  {
		Activity activity = createActivity(projectLeader, name, start, end, duration);
		
		return activity;
	}
	
	private Activity createActivity(Employee projectLeader, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException {
		if (hasProjectLead()) {
			if (projectLead.equals(this.getProjectLead())) {
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
	//hasProjectLead

}
