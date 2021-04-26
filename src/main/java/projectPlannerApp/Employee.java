package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	public String initials;
	public List<Activity> currActivities = new ArrayList<Activity>();

	public Employee(String initials) {
		this.initials = initials;
	}
	
	public boolean isAvailable() {
		return (currActivities.size() < 20);
	}
	
}
