package projectPlannerApp;

import java.time.Year;
import java.util.List;
import java.util.ArrayList;

public class Project {
	
	public List<Activity> activities = new ArrayList<Activity>();
	private ProjectLeadException errorMessage = new ProjectLeadException("Project lead is already assigned");

	
	public String name;
	public String projectNumber;
	public Calendar projectStart;
	public Employee projectLead = null;	
	
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
	
	public Activity newActivity(Employee projectLead, String name, int start, int end, int duration) throws ProjectLeadException  {
		if (projectLead.equals(this.getProjectLead())) {
			Activity activity = new Activity(name, start, end, duration);
			activities.add(activity);
			
			return activity;
		}
		else {
			throw errorMessage;
		}
	}
	
	public Employee getProjectLead() {
		return projectLead;
	}
	
	public void setProjectLead(Employee employee) throws ProjectLeadException {
		if (projectLead == null) {
			this.projectLead = employee;
		}
		else {
			throw errorMessage;
		}
		
	}
	
	
	public boolean hasProjectLead() {
		if (projectLead == null) return false;
		return true; 
	}

	
}
