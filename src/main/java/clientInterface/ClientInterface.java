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
	public static List<Date> dates;
	public static List<TimeRegistration> registrations;

	public static String initials, name;
	public static Employee client, employee;
	public static Project project;
	public static Activity activity;
	public static ActivityCalendar calendar;
	public static Date date;
	public static TimeRegistration registration;

	public static int selector;

	public static void main(String[] args) throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		showLogin();
		sc.close();
		System.exit(0);
	}
	

	public static void showLogin() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		initials = Validators.stringValidator("initials");
		client = app.getEmployee(initials.toUpperCase());
		
		showProjectPlannerApp();
	}

	public static void showProjectPlannerApp() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {   	
		Options.subpaths.add("->Project Planner App");

		appLoop: while (true) {
			Options.printProjectPlannerApp();
			
			selector = Validators.rangeValidator(7);
			switch(selector) {

				case 0: // Switch user
					Options.subpaths.remove(Options.subpaths.size()-1);
					showLogin();
					break appLoop;
				case 1: // view client
					employee = client;
					showEmployee();
					break;
				case 2: // View employees
					employees = new ArrayList<Employee>(app.getEmployees().values());
					showEmployeeOverview();
					break;

				case 3: // View projects
					projects = app.getProjects();
					showProjectOverview();
					break;

				case 4:  // View app calendar
					calendar = app.getCalendar();
					dates = new ArrayList(calendar.getDates().values());
					showCalendar();
					break;

				case 5: // Create employee
					Modifiers.createEmployee();
					break;

				case 6: // Create project
					Modifiers.createProject();
					break;
					
				case 7: // close program
					Options.subpaths.remove(Options.subpaths.size()-1);
					break appLoop;
			}
		}
	}

	public static void showEmployeeOverview() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		employee = null;
		Options.subpaths.add("->Employee Overview");

		employeeOverviewLoop: while (true) {
			Options.printEmployeeOverview();
			
			selector = Validators.rangeValidator(employees.size()+1);
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break employeeOverviewLoop;
					
				case 1: // Search employee
					initials = Validators.stringValidator("search");
					employees = app.searchEmployees(initials);
					break;
					
				default: // Select employee
					employee = employees.get(selector-2);
					showEmployee();
					break;
			}
		}
	}

	public static void showProjectOverview() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		project = null;
		Options.subpaths.add("->Project Overview");

		projectOverviewLoop: while (true) {
			Options.printProjectOverview();  
			
			selector = Validators.rangeValidator(projects.size());
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break projectOverviewLoop;

				default: // Select project
					project = projects.get(selector-1);
					showProject();
					break;
			}
		}
	}

	public static void showActivityOverview() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		activity = null;
		Options.subpaths.add("->Activity Overview");

		activityOverviewLoop: while (true) {
			Options.printActivityOverview();    
			
			selector = Validators.rangeValidator(activities.size());
			switch(selector) {
			case 0: // close/confirm
				Options.subpaths.remove(Options.subpaths.size()-1);
				break activityOverviewLoop;

			default: // Select activity
				activity = activities.get(selector-1);
				showActivity(); 
				break;
			}
		}
	}
	
	public static void showDateOverview() throws OperationNotAllowedException, ProjectLeadException {
		date = null;
		Options.subpaths.add("->Date Overview");

		dateOverviewLoop: while (true) {
			Options.printDateOverview();
			
			selector = Validators.rangeValidator(dates.size());
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break dateOverviewLoop;

				default: // Select date
					date = dates.get(selector-1);
					showDate(); 
					break;
			}
		}
	}
	
	public static void showTimeRegistrationOverview() throws OperationNotAllowedException, ProjectLeadException {
		registration = null;
		Options.subpaths.add("->Time Registration Overview");

		registrationOverviewLoop: while (true) {
			Options.printTimeRegistrationOverview();
			
			selector = Validators.rangeValidator(registrations.size());
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break registrationOverviewLoop;

				default: // Select registration
					registration = registrations.get(selector-1);
					showTimeRegistration(); 
					break;
			}
		}
	}
	
	public static void showEmployee() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Employee " + employee.toString());

		employeeLoop: while (true) {
			Options.printEmployee();
			
			selector = Validators.rangeValidator(2);
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break employeeLoop;
					
				case 1: // view activities
					activities = employee.getActivities();
					showActivityOverview();
					break;
					
				case 2: // view projects
					projects = employee.getProjects();
					showProjectOverview();
					break;
			}
		}
	}
	
	public static void showProject() throws OperationNotAllowedException,ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Project " + project.getProjectNumber());

		projectLoop: while(true){
			Options.printProject();
			selector = Validators.rangeValidator(6);
			switch(selector) {
				case 0: //close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break projectLoop;
					
				case 1:	//create activity 
					Modifiers.createActivity();
					break;
					
				case 2: //remove activity
					Modifiers.removeActivity();
					break;
				
				case 3: //Activities overview
					activities = new ArrayList(project.getActivities().values());
					showActivityOverview();
					break;
				
				case 4: //Set project lead
					Modifiers.setProjectLead();
					break;
				
				case 5: //Set project start
					Modifiers.changeProjectStart();
					break;
				
				case 6: //Set project name
					Modifiers.changeProjectName();
					break;
			}
		}
	}
	
	public static void showActivity() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Activity " + activity.getName());

		activityLoop: while(true){
			Options.printActivity();
			selector = Validators.rangeValidator(7);
			switch(selector){
				case 0: //Close
					Options.subpaths.remove(Options.subpaths.size()-1);
					break activityLoop;
					
				case 1: //Employeeoverview
					employees = activity.getEmployees();
					showEmployeeOverview();
					break;
					
				case 2: //Calendar
					calendar = activity.getCalendar();
					showDateOverview();
					break; 
					
				case 3: //set time frame
					Modifiers.changeActivityTimeFrame();
					break;
					
				case 4: //Set name
					Modifiers.changeActivityName();
					break; 
				case 5: //Add Employee
					Modifiers.addEmployee();
					break;
					
				case 6: //Remove employee
					Modifiers.removeEmployee();
					break;
					
				case 7: //Create time registration
					Modifiers.createTimeRegistration();
					break;
			}
		}
	}
	
	public static void showCalendar() throws OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->Activity calendar");

		calendarLoop: while (true) {
			Options.printCalendar();
			
			selector = Validators.rangeValidator(2);
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break calendarLoop;
					
				case 1: // view dates
					dates = new ArrayList(calendar.getDates().values());
					showDateOverview();
					break;
					
				case 2: // view time registrations
					registrations = calendar.getTimeRegistrations();
					showTimeRegistrationOverview();
					break;
			}
		}
	}
	
	public static void showDate() throws OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->" + date.toString());

		dateLoop: while (true) {
			Options.printDate();
			
			selector = Validators.rangeValidator(1);
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break dateLoop;
					
				case 1: // view time registrations
					registrations = date.getTimeRegistrations();
					showTimeRegistrationOverview();
					break;
			}
		}
	}
	
	public static void showTimeRegistration() throws OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->Time Registration " + registration.toString());

		registrationLoop: while (true) {
			Options.printTimeRegistration();
			
			selector = Validators.rangeValidator(2);
			switch(selector) {
				case 0: // close/confirm
					Options.subpaths.remove(Options.subpaths.size()-1);
					break registrationLoop;
					
				case 1: // change time registration date
					date = Validators.dateValidator("registration");
					Modifiers.changeTimeRegistrationDate();
					break;
					
				case 2: // change time registration hours
					double hours = Validators.hoursValidator();
					Modifiers.changeTimeRegistrationHours();
					break;
			}
		}		
	}
}
