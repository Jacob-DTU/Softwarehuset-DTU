package projectPlannerApp;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	
	public List<Employee> employees = new ArrayList<Employee>();
	private TooManyActivitiesException errorMessage = new TooManyActivitiesException("Employee is unavailable during the given timeframe");
	
	private String name;
	private int start, end, duration;
	
	public Activity(String name, int start, int end, int duration) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.duration = duration;
	}
	
	public void addEmployee(Employee employee) throws TooManyActivitiesException {
		if (employee.currActivities.size() == 20) {
			throw errorMessage;
		}
		else {
			employees.add(employee);
			employee.currActivities.add(this);
		}
	}

}
