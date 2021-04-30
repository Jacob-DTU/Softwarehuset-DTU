package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	public String initials;
	public List<Activity> currActivities = new ArrayList<Activity>();
	public List<Project> leadProjects = new ArrayList<Project>();

	public Employee(String initials) {
		this.initials = initials;
	}
	
	public boolean isAvailable() {
		return (currActivities.size() < 20);
	}
	//addActivity
	//getTimeframe (Giver nok ikke mening i forhold til employee)
	//timeSpent
	//calendar
	//getTimeSpent
	//getActivity
	//getEmployee 


}
