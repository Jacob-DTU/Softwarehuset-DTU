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
	
	public static String initials;
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
    	Options.printProjectPlannerApp();
        selector = Validators.numberValidator(4);
        switch(selector) {
         
        case 0: // Switch user
            showLogin();
            
        case 1: // View projects
        	showEmployeeOverview();
            
        case 2: // View employees
            showProjectOverview();
       
        case 3:  // View app calendar
            showCalendar(app.calendar);
            
        case 4: // Create project
        	Modifiers.createProject();
       
        }
    }
    
    public static void showEmployeeOverview() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printEmployeeOverview();
    	selector = Validators.numberValidator(app.getEmployees().size());
    	
    	switch(selector) {
    	case 0: // Main menu
    		showProjectPlannerApp();
    		
    	default: // Select project
    		employee = app.getEmployees().get(selector-1);
    	}
    }
    
    public static void showProjectOverview() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printProjectOverview();        
        selector = Validators.numberValidator(app.getProjects().size());
        
        switch(selector) {
        case 0: // Main menu
            showProjectPlannerApp();
        
        default: // Select project
        	project = app.getProjects().get(selector-1);
        	showProject(project); 
        }
    }
    
    public static void showActivityOverview() throws ProjectLeadException, OperationNotAllowedException {
    	Options.printActivityOverview();        
        selector = Validators.numberValidator(project.getActivities().size());
        
        switch(selector) {
        case 0: // Main menu
            showProjectPlannerApp();
        
        default: // Select activity
        	activity = project.getActivities().get(selector-1);
        	showActivity(activity); 
        }
    }
    
    public static void showCalendar(ProjectPlannerCalendar currCalendar) {
    	calendar = currCalendar;
    	Options.printCalendar(calendar);
    }
    
    public static void showEmployee(Employee currEmployee) {
    	employee = currEmployee;
    	Options.printEmployee(employee);
    }
    
    public static void showProject(Project currProject) throws ProjectLeadException, OperationNotAllowedException {
    	project = currProject;
    	Options.printProject(project);
    }
    
    public static void showActivity(Activity currActivity) {
    	activity = currActivity;
    	Options.printActivity(activity);
    }
    
    public static void showDate(Date currDate) {
    	date = currDate;
    	Options.printDate(date);
    }
    
    public static void showTimeRegistration(TimeRegistration currRegistration) {
    	registration = currRegistration;
		Options.printTimeRegistration(registration);
		
	}
    
    
    
    
	
}
