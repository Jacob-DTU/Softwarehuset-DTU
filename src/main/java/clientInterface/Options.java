package clientInterface;

import java.util.ArrayList;
import java.util.List;

import projectPlannerApp.*;
import projectPlannerCalendar.*;

public class Options extends ClientInterface {
	
	public static List<String> subpaths = new ArrayList<String>();
	private static String path;
	
	/*
	 * This class contains all visual parts of the interface
	 */
	public static void printSectionLine() {
		path = "";
		for (String subpath : subpaths) {
			path += subpath;
		}
		
		System.out.println();
		System.out.println("_".repeat(path.length()+4));
		System.out.println(client.getInitials() + path);
		System.out.println("_".repeat(path.length()+4));
	}
	
	private static void printCloseConfirm() {
		System.out.println("\n 0. Close/Confirm");
	}
	
	public static void printProjectPlannerApp() {
		printSectionLine();
		
		System.out.println("\n 0. Switch user");
		System.out.println(" 1. View client profile");
		System.out.println(" 2. View employees");
    	System.out.println(" 3. View projects");
    	System.out.println(" 4. View app calendar");
    	System.out.println(" 5. Create new Employee");
		System.out.println(" 6. Create new project");
		System.out.println(" 7. Close program");
	}
	
	public static void printEmployeeOverview() {
		printSectionLine();

		printCloseConfirm();
		System.out.println(" 1. Search employee");
		int i = 2;
		for (Employee emp : employees) {
			if (i < 10) {
				System.out.println(" " + i++ + ". " + emp.toString());
			}
			else {
				System.out.println(i++ + ". " + emp.toString());
			}
		}
	}
	
	public static void printProjectOverview() {
		printSectionLine();

		printCloseConfirm();
    	int i = 1;
        for (Project proj : projects) {
        	if (i < 10) {
				System.out.println(" " + i++ + ". " + proj.toString());
			}
			else {
				System.out.println(i++ + ". " + proj.toString());
			}
        }
	}
	
	public static void printActivityOverview() {
		printSectionLine();

		printCloseConfirm();
		int i = 1;
        for (Activity act : activities) {
        	if (i < 10) {
				System.out.println(" " + i++ + ". " + act.toString());
			}
			else {
				System.out.println(i++ + ". " + act.toString());
			}
        }
		
	}
	
	public static void printDateOverview() {
		printSectionLine();

		printCloseConfirm();
		int i = 1;
        for (Date dat : dates) {
        	if (i < 10) {
				System.out.println(" " + i++ + ". " + dat.toString());
			}
			else {
				System.out.println(i++ + ". " + dat.toString());
			}
        }
	}
	
	public static void printTimeRegistrationOverview() {
		printSectionLine();

		printCloseConfirm();
		int i = 1;
        for (TimeRegistration reg : registrations) {
        	if (i < 10) {
				System.out.println(" " + i++ + ". " + reg.toString());
			}
			else {
				System.out.println(i++ + ". " + reg.toString());
			}
        }
	}
		
	public static void printEmployee() {
		printSectionLine();

		System.out.println("\nEmployee initials : " + employee.toString());
		printCloseConfirm();
		System.out.println(" 1. View activities for this employee");
		System.out.println(" 2. View projects lead by this employee");
	}
	
	public static void printProject() {
		printSectionLine();

		System.out.println("\n" + project.toString());
		printCloseConfirm();
		System.out.println(" 1. Create Activity");
    	System.out.println(" 2. Remove Activity");
    	System.out.println(" 3. View Activities");
    	System.out.println(" 4. Set Project lead");
		System.out.println(" 5. Set Project start");
		System.out.println(" 6. Set Project name");
	}
	
	public static void printActivity() {
		printSectionLine();

		System.out.println("\n" + activity.toString());
		printCloseConfirm();
		System.out.println(" 1. Employee overview");
    	System.out.println(" 2. View calendar");
    	System.out.println(" 3. Change activity timeframe");
    	System.out.println(" 4. Set activity name");
		System.out.println(" 5. Add employee");
		System.out.println(" 6. Remove employee");
		System.out.println(" 7. Create time registration");
	}
	
	public static void printCalendar() {
		printSectionLine();

		System.out.println("\n" + calendar.toString());
		printCloseConfirm();
		System.out.println(" 1. View dates");
		System.out.println(" 2. View all time registrations");
	}
	
	public static void printDate() {
		printSectionLine();

		System.out.println("\n" + date.toString());
		printCloseConfirm();
		System.out.println(" 1. View time registrations");
	}
	
	public static void printTimeRegistration() {
		printSectionLine();

		System.out.println("\n" + registration.toString());
		printCloseConfirm();
		System.out.println(" 1. Change registration date");
		System.out.println(" 2. change registered hours");
	}
	
	public static void printCreateProject() {
		printSectionLine();
		printCloseConfirm();

		System.out.println(" 1. Make a new project with a name");
        System.out.println(" 2. Make a new project with a name and starting date");
        System.out.println(" 3. Make a new project with a name and project lead");
        System.out.println(" 4. Make a new project with a name, project lead and starting date");
	}
	
	public static void printCreateActivity() {
		printSectionLine();
		printCloseConfirm();
		
		System.out.println(" 1. Make a new activity with a name");
		System.out.println(" 2. Make a new activity with a name, starting date, end date and duration");
	}
	
	public static void printChangeActivityDuration() {
		System.out.println("Enter a duration for the activity");
	}
	
	// ------------Error messages------------ //
	
	public static void printNoEmployeeSelected() {
		System.out.println("No employee selected");
	}

	public static void printNoActivitySelected() {
		System.out.println("No activity selected");
	}

	public static void printActivityContainsEmployee() {
		System.out.println("Employee was already added to activity " + activity.getName());		
	}
}
