package clientInterface;

import projectPlannerApp.*;
import projectPlannerCalendar.*;

import java.util.NoSuchElementException;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.print.attribute.IntegerSyntax;


public class ClientInterface {
	
	public static final Scanner sc = new Scanner(System.in);
	public static final ProjectPlannerApp app = new ProjectPlannerApp();
	
	public static List<Employee> employees;
	public static List<Project> projects;
	public static List<Activity> activities;
	
	public static String initials, name;
	public static Employee client, employee;
	public static Project project;
	public static Activity activity;
	public static ProjectPlannerCalendar calendar;
	public static Date date;
	private static TimeRegistration registration;
	
	public static int selector;
	
    public static void main(String[] args) throws OperationNotAllowedException, ProjectLeadException {
    	showLogin();
    }
    
    public static void showLogin() throws OperationNotAllowedException, ProjectLeadException {
    	String initials = Validators.stringValidator("initials");	
		client = app.getEmployee(initials.toUpperCase());
		showProjectPlannerApp();
    }
    
    public static void showProjectPlannerApp() throws OperationNotAllowedException, ProjectLeadException {   	
    	//while (true) {
	    	Options.printProjectPlannerApp();
	        selector = Validators.rangeValidator(4);
	        switch(selector) {
	         
	        case 0: // Switch user
	            showLogin();
	            
	        case 1: // View employees
	        	employees = new ArrayList<Employee>(app.getEmployees().values());
	        	showEmployeeOverview();
	        	showEmployee();
	            
	        case 2: // View projects
	        	projects = app.getProjects();
	            showProjectOverview();
	            showProject();
	       
	        case 3:  // View app calendar
	        	calendar = app.getCalendar();
	            showCalendar();
	            
	        case 4: // Create project
	        	Modifiers.createProject();
	        }
        //}
    }
    
    public static void showEmployeeOverview() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printEmployeeOverview();

    	selector = Validators.rangeValidator(employees.size());
    	
    	switch(selector) {
    	case 0: // Main menu
    		showProjectPlannerApp();
    		
    	default: // Select employee
    		employee = app.getEmployees().get(selector-1);
    	}
    }
    
    public static void showProjectOverview() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printProjectOverview();        
        selector = Validators.rangeValidator(app.getProjects().size());
        
        switch(selector) {
        case 0: // Main menu
            showProjectPlannerApp();
        
        default: // Select project
        	project = app.getProjects().get(selector-1);
        	showProject(); 
        }
    }
    
    public static void showActivityOverview() throws ProjectLeadException, OperationNotAllowedException {
    	Options.printActivityOverview();        
        selector = Validators.rangeValidator(project.getActivities().size());
        
        switch(selector) {
        case 0: // Main menu
            showProjectPlannerApp();
        
        default: // Select activity
        	activity = project.getActivities().get(selector-1);
        	showActivity(); 
        }
    }
    
    public static void showCalendar() {
    	Options.printCalendar(calendar);
    }
    
    public static void showEmployee() {
    	Options.printEmployee(employee);
    }
    
    public static void showProject() throws ProjectLeadException, OperationNotAllowedException {
    	Options.printProject(project);
    }
    
    public static void showActivity() {
    	Options.printActivity(activity);
    }
    
    public static void showDate() {
    	Options.printDate(date);
    }
    
    public static void showTimeRegistration() {
		Options.printTimeRegistration(registration);
	}

}
