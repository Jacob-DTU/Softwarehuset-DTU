package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

public class Employee {
		
	private String initials;
	
	public List<Activity> currActivities = new ArrayList<Activity>();
	public List<Project> leadProjects = new ArrayList<Project>();
	
	public Employee(String initials) {
		this.initials = initials;
	}
	
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	public boolean isAvailable() {
		return (currActivities.size() < 20);
	}
	public String toString(){
		return getInitials();
	}
}
