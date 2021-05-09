package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

public class Employee {
		
	private String initials;
	
	private List<Activity> currActivities = new ArrayList<Activity>();
	private List<Project> leadProjects = new ArrayList<Project>();
	
	public Employee(String initials) {
		this.initials = initials;
	}
	
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	public List<Activity> getActivities() {
		return currActivities;
	}
	
	public List<Project> getProjects() {
		return leadProjects;
	}
	
	public boolean isAvailable() {
		return (currActivities.size() < 20);
	}
	public String toString(){
		return getInitials();
	}
}
