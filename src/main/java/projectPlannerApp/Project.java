package projectPlannerApp;

import java.time.Year;
import projectPlannerCalendar.Date;
import java.util.HashMap;

public class Project {

	/*
	 * Project object: Jacob
	 */
	
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
		setProjectLead(projectLead);
	}
	
	public Project(String name, int numberOfProjects, Employee projectLead, Date date) throws ProjectLeadException {
		this.name = name;
		this.projectNumber = String.format("%ty", Year.now()) + String.format("%04d", numberOfProjects);
		setProjectLead(projectLead);
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
	
	public void setProjectLead(Employee employee) throws ProjectLeadException { //Tobias
		if (hasProjectLead()) {
			throw alreadyProjectLeadError;
		}
		else {
			projectLead = employee;
			employee.getProjects().add(this);
		}		
	}
	
	public Activity newActivity(Employee client, String name) throws ProjectLeadException, OperationNotAllowedException {//Simon
		Activity activity = createActivity(client, name, -1, -1, -1);
		
		return activity;
	}
	
	public Activity newActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException  {//Simon
		Activity activity = createActivity(client, name, start, end, duration);
		
		return activity;
	}
	

	
	private Activity createActivity(Employee client, String name, int start, int end, int duration) throws ProjectLeadException, OperationNotAllowedException {//Simon 
		assert !client.equals(null) && !name.equals(null): "Precondition";
		Activity activity;
		if (hasProjectLead()) {
			if (client.equals(projectLead)) {//1
				if (activities.containsKey(name)) {//2
					throw nameAlreadyAssignedError;
				} else {
					if (start < 0 || end < 0 || duration < 0) {//3
						activity = new Activity(name);
					} else {
						activity = new Activity(name, start, end, duration);
					}
					activity.setAssociatedProject(this);
					activities.put(name, activity); 
				}
			}
			else {
				throw notProjectLeadError;
			}
		}
		else {
			throw noProjectLeadError;
		}
		assert activities.containsValue(activity) && ((activity.getStart() == start && activity.getEnd() == end &&
		 activity.getDuration() == duration && activity.getName().equals(name)) ||
		 (activity.getName().equals(name) &&activity.getStart() == 0 && activity.getEnd() == 0 && activity.getDuration() == 0)): "Postcondition";
		return activity;
	}
	
	public boolean hasProjectLead() { //Christopher
		if (projectLead == null) return false;
		return true; 
	}
	


	public boolean contains(Activity activity) { //Christopher
		if (activities.containsValue(activity)) {
			return true;
		}
		return false;
	}
	
	public void removeActivity(Activity activity) throws OperationNotAllowedException{ //Christopher
		if (this.contains(activity)) {
			activities.remove(activity.getName());
		}
		else {
			throw noSuchNameError;
		}	
	}
	
	public String toString() { //Christopher
		String startText = "";
		String leadText = "";
		if(projectStart != null){
			startText =  ", ProjectStart : " + getProjectStart().toString();
		}
		if(projectLead != null){
			leadText = ", ProjectLead : " + getProjectLead().toString();
		}
		return projectNumber + " : " + name + startText + leadText;
	}

}

