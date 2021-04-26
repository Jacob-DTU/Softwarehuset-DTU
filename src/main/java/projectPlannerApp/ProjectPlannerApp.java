package projectPlannerApp;

import java.util.*;
public class ProjectPlannerApp {
	public List<Employee> employees = new ArrayList<Employee>();
	public List<Project> projects = new ArrayList<Project>();
	
//	public static void main(String[] args) {
//	}
	
	public Project newProject(String name) {
		Project newProject = new Project(name, projects.size()+1);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Calendar projectStart) {
		Project newProject = new Project(name, projects.size()+1, projectStart);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Employee newEmployee(String initials) {
		Employee newEmployee = new Employee(initials);
		employees.add(newEmployee);
		
		return newEmployee;
	}
	
}
