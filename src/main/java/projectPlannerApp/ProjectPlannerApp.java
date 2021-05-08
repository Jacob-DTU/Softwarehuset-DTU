package projectPlannerApp;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import projectPlannerCalendar.Date;
import projectPlannerCalendar.ActivityCalendar;

public class ProjectPlannerApp {
	
	private OperationNotAllowedException employeeAlreadyExistsError = new OperationNotAllowedException("An employee with these initials already exists");
	
	public Map<String, Employee> employees = new HashMap<String, Employee>();
	public List<Project> projects = new ArrayList<Project>();
	
	public ActivityCalendar calendar = new ActivityCalendar();
	public List<Project> leadProjects = new ArrayList<Project>();
	
	public Activity vacation = new Activity("Vacation");
	
	public ProjectPlannerApp() {
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
	
	public Project newProject(String name) {
		Project newProject = new Project(name, projects.size()+1);
		projects.add(newProject);
		return newProject;
	}
	
	public Project newProject(String name, Date date) {
		Project newProject = new Project(name, projects.size()+1, date);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Employee projectLead) throws ProjectLeadException {
		Project newProject = new Project(name, projects.size()+1, projectLead);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Employee projectLead, Date date) throws ProjectLeadException {
		Project newProject = new Project(name, projects.size()+1, projectLead, date);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Employee newEmployee(String initials) throws OperationNotAllowedException {
		Employee newEmployee = new Employee(initials);
		if (this.contains(newEmployee)) {
			throw employeeAlreadyExistsError;
		}
		employees.put(initials, newEmployee);
		
		return newEmployee;
	}
	
	public TimeRegistration newTimeRegistration(Date start, Date end, Employee employee) {
		return vacation.calendar.newTimeRegistration(start, end, employee);
	}
	
	public List<Employee> searchEmployees(String initials){
		return Search.searchEmployees(employees, initials);
	}

	public boolean contains(Employee employee) {
		return employees.containsKey(employee.getInitials());
	}
	
}
