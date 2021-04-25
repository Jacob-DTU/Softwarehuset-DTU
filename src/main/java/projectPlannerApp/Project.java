package projectPlannerApp;

import java.time.Year;
import java.util.List;
import java.util.ArrayList;

public class Project {
	
	private List<Activity> activities = new ArrayList<Activity>();
	
	public String name;
	public String projectNumber;
	public Calendar projectStart;
	public Employee projectLeader = null;	
	
	public Project(String name, int numberOfProjects) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		
	}
	
	public Project(String name, int numberOfProjects, Calendar projectStart) {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		this.projectStart = projectStart;
	}
	
	public void newActivity(String name, int start, int end, int duration) {
		activities.add(new Activity(name, start, end, duration));
		
	}
	
}
