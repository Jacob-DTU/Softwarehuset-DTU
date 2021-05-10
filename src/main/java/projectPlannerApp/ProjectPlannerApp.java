package projectPlannerApp;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import projectPlannerCalendar.Date;
import projectPlannerCalendar.ActivityCalendar;

public class ProjectPlannerApp {
	/*
	 * ProjectPlannerApp : Tobias
	 */

	private OperationNotAllowedException employeeAlreadyExistsError = new OperationNotAllowedException("An employee with these initials already exists");
	
	private Map<String, Employee> employees = new HashMap<String, Employee>();
	private List<Project> projects = new ArrayList<Project>();
	
	private ActivityCalendar calendar = new ActivityCalendar();  
	
	private Activity vacation;
	
	public ProjectPlannerApp() {
		vacation = new Activity("Vacation");
	}
	
	public Map<String, Employee> getEmployees() {
		return employees;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public ActivityCalendar getCalendar() {
		return calendar;
	}
	
	public Employee getEmployee(String initialer) throws OperationNotAllowedException {
		if (employees.containsKey(initialer)) {
			return employees.get(initialer);
		}
		return newEmployee(initialer);
	}
	
	public Project newProject(String name) {//Simon
		Project newProject = new Project(name, projects.size()+1);
		projects.add(newProject);
		return newProject;
	}
	
	public Project newProject(String name, Date date) {//Simon
		Project newProject = new Project(name, projects.size()+1, date);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Employee projectLead) throws ProjectLeadException {//Simon
		Project newProject = new Project(name, projects.size()+1, projectLead);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Employee projectLead, Date date) throws ProjectLeadException {//Simon
		Project newProject = new Project(name, projects.size()+1, projectLead, date);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Employee newEmployee(String initials) throws OperationNotAllowedException {//Simon
		Employee newEmployee = new Employee(initials.toUpperCase());
		if (this.contains(newEmployee)) {
			throw employeeAlreadyExistsError;
		}
		employees.put(initials.toUpperCase(), newEmployee);
		
		return newEmployee;
	}
	
	public TimeRegistration newTimeRegistration(Date start, Date end, Employee employee) { //Christopher
		return vacation.getCalendar().newTimeRegistration(start, end, employee);
	}
	
	public List<Employee> searchEmployees(String initials){ //Jacob
		return Search.searchEmployees(employees, initials);
	}

	public boolean contains(Employee employee) { //Jacob
		return employees.containsKey(employee.getInitials());
	}
	public void removeEmployee(String initials){ //Jacob
		employees.remove(initials);
	}
	public void removeProject(Project project){ // Jacob
		projects.remove(project);
	}
	
}
