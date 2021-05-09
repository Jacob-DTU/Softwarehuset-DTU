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
	
	/*
	 * This class consists of methods for the client to access/view objects
	 */

	public static final Scanner sc = new Scanner(System.in);
	public static final ProjectPlannerApp app = new ProjectPlannerApp();

	public static List<Employee> employees;
	public static List<Project> projects;
	public static List<Activity> activities;

	public static String initials, name;
	public static Employee client, employee;
	public static Project project;
	public static Activity activity;
	public static ActivityCalendar calendar;
	public static Date date;
	public static TimeRegistration registration;

	public static int selector;

	public static void main(String[] args) throws OperationNotAllowedException, ProjectLeadException {
		mainLoop: while (true) {
			Options.printMain();
			
			selector = Validators.rangeValidator(1);
			switch(selector) {
			case 0: // Close program
				break mainLoop;
			case 1: // Login
				showLogin();
			}
		}
		sc.close();
	}
	

	public static void showLogin() throws OperationNotAllowedException, ProjectLeadException {
		String initials = Validators.stringValidator("initials");	
		client = app.getEmployee(initials.toUpperCase());

		showProjectPlannerApp();
	}

	public static void showProjectPlannerApp() throws OperationNotAllowedException, ProjectLeadException {   	
		appLoop: while (true) {
			Options.printProjectPlannerApp();
			
			selector = Validators.rangeValidator(5);
			switch(selector) {

			case 0: // Switch user
				break appLoop;

			case 1: // View employees
				employees = new ArrayList<Employee>(app.getEmployees().values());
				showEmployeeOverview();
				showEmployee();
				break;

			case 2: // View projects
				projects = app.getProjects();
				showProjectOverview();
				showProject();
				break;

			case 3:  // View app calendar
				calendar = app.getCalendar();
				showCalendar();
				break;

			case 4: // Create project
				Modifiers.createProject();
				break;

			case 5: // Create employee
				Modifiers.createEmployee();
				break;
			}
		}
	}

	public static void showEmployeeOverview() throws OperationNotAllowedException, ProjectLeadException {
		employeeOverviewLoop: while (true) {
			Options.printEmployeeOverview();

			selector = Validators.rangeValidator(employees.size());
			switch(selector) {
			case 0: // close/confirm
				break employeeOverviewLoop;
			case 1: // Search employee
				initials = Validators.stringValidator("search");
				employees = app.searchEmployees(initials);
				showEmployeeOverview();
				break;
			default: // Select employee
				employee = app.getEmployees().get(selector-2);
				showEmployee();
				break;
			}
		}
	}

	public static void showProjectOverview() throws OperationNotAllowedException, ProjectLeadException {
		projectOverviewLoop: while (true) {
			Options.printProjectOverview();  
			
			selector = Validators.rangeValidator(app.getProjects().size());
			switch(selector) {
			case 0: // close/confirm
				break projectOverviewLoop;

			default: // Select project
				project = app.getProjects().get(selector-1);
				showProject();
				break;
			}
		}
	}

	public static void showActivityOverview() throws OperationNotAllowedException, ProjectLeadException {
		activityOverviewLoop: while (true) {
			Options.printActivityOverview();    
			
			selector = Validators.rangeValidator(project.getActivities().size());
			switch(selector) {
			case 0: // close/confirm
				break activityOverviewLoop;

			default: // Select activity
				activity = project.getActivities().get(selector-1);
				showActivity(); 
				break;
			}
		}
	}
	
	// not done
	public static void showCalendar() throws OperationNotAllowedException, ProjectLeadException {
		calendarLoop: while (true) {
			Options.printCalendar(calendar);
			selector = Validators.rangeValidator(0);
			
		}
	}
	
	// not done
	public static void showEmployee() throws OperationNotAllowedException, ProjectLeadException {
		Options.printEmployee(employee);
		selector = Validators.rangeValidator(0);
	}
	
	// not done
	public static void showProject() throws OperationNotAllowedException,ProjectLeadException {
		Options.printProject(project);
		selector = Validators.rangeValidator(0);
		
		showProjectPlannerApp();
	}
	
	// not done
	public static void showActivity() throws OperationNotAllowedException, ProjectLeadException {
		Options.printActivity(activity);
		selector = Validators.rangeValidator(0);
		
		showProjectPlannerApp();
	}
	
	// not done
	public static void showDate() throws OperationNotAllowedException, ProjectLeadException {
		Options.printDate(date);
		selector = Validators.rangeValidator(0);
		
		showProjectPlannerApp();
	}
	
	// not done
	public static void showTimeRegistration() throws OperationNotAllowedException, ProjectLeadException {
		Options.printTimeRegistration(registration);
		selector = Validators.rangeValidator(0);
		
		showProjectPlannerApp();
	}

}
