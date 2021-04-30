package projectPlannerApp;

import java.util.*;
public class ProjectPlannerApp {
	public List<Employee> employees = new ArrayList<Employee>();
	public List<Project> projects = new ArrayList<Project>();
	//Array af matches
	//Calandar object.
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
	
	public Project newProject(String name, Employee projectLead) throws ProjectLeadException {
		Project newProject = new Project(name, projects.size()+1, projectLead);
		projects.add(newProject);
		
		return newProject;
	}
	
	public Project newProject(String name, Employee projectLead, Calendar projectStart) throws ProjectLeadException {
		Project newProject = new Project(name, projects.size()+1, projectLead, projectStart);
		projects.add(newProject);
		
		return newProject;
	}
	
	
	public Employee newEmployee(String initials) {
		Employee newEmployee = new Employee(initials);
		employees.add(newEmployee);
		
		return newEmployee;
	}

	 
	
}
