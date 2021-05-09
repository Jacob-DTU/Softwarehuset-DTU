package clientInterface;

import java.util.List;

import projectPlannerApp.*;
import projectPlannerCalendar.*;

public class Options extends ClientInterface {
	
	/*
	 * This class contains all visual parts of the interface
	 */
	
	private static void printCloseConfirm() {
		System.out.println("\n 0. Close/Confirm");
	}
	
	public static void printProjectPlannerApp() {
		System.out.println("\n 0. Switch user");
		System.out.println(" 1. View employees");
    	System.out.println(" 2. View projects");
    	System.out.println(" 3. View global calendar");
    	System.out.println(" 4. Create new Employee");
		System.out.println(" 5. Create new project");
		System.out.println(" 6. Close program");
	}
	
	public static void printEmployeeOverview() {
		printCloseConfirm();
		System.out.println(" 1. Search employee");
		int i = 2;
		for (Employee employee : employees) {
			if (i < 10) {
				System.out.println(" " + i++ + ". " + employee.toString());
			}
			else {
				System.out.println(i++ + ". " + employee.toString());
			}
		}
	}
	
	public static void printProjectOverview() {
		printCloseConfirm();
    	int i = 1;
        for (Project project : projects) {
        	if (i < 10) {
				System.out.println(" " + i++ + ". " + project.toString());
			}
			else {
				System.out.println(i++ + ". " + project.toString());
			}
        }
	}
	
	public static void printActivityOverview() {
		printCloseConfirm();

	}
	
	public static void printDateOverview() {
		printCloseConfirm();
	}
	
	public static void printTimeRegistrationOverview() {
		printCloseConfirm();
	}
		
	public static void printEmployee() {
		System.out.println(employee.toString());
		printCloseConfirm();
		System.out.println(" 1. View activities for this employee");
		System.out.println(" 2. View projects lead by this employee");
	}
	
	public static void printProject() {
		System.out.println(project.toString());
		printCloseConfirm();
		System.out.println(" 1. Create Activity");
    	System.out.println(" 2. Remove Activity");
    	System.out.println(" 3. View Activities");
    	System.out.println(" 4. Set Project lead");
		System.out.println(" 5. Set Project start");
		System.out.println(" 6. Set Project name");
	}
	
	public static void printActivity() {
		System.out.println(activity.toString());
		printCloseConfirm();
	}
	
	public static void printCalendar() {
		System.out.println(calendar.toString());
		printCloseConfirm();
	}
	
	public static void printDate() {
		System.out.println(date.toString());
		printCloseConfirm();
	}
	
	public static void printTimeRegistration() {
		System.out.println(registration.toString());
		printCloseConfirm();
	}
	
	public static void printCreateProject() {
		printCloseConfirm();

		System.out.println(" 1. Make a new project with a name");
        System.out.println(" 2. Make a new project with a name and starting date");
        System.out.println(" 3. Make a new project with a name and project lead");
        System.out.println(" 4. Make a new project with a name, project lead and starting date");
	}
	
	public static void printCreateActivity() {
		printCloseConfirm();
		
		System.out.println(" 1. Make a new activity with a name");
		System.out.println(" 2. Make a new activity with a name, starting date, end date and duration");
	}
	
	public static void printChangeActivityStart() {
		System.out.println("Set a week number for at starting week");
		
	}
	
	public static void printChangeActivityEnd() {
		System.out.println("Set a week number for at ending week");

	}
	
	public static void printChangeActivityDuration() {
		System.out.println("Set a hour number for number of hours this activity might take");

	}
	
	// ------------Error messages------------ //
	
	public static void printNoEmployeeSelected() {
		System.out.println("No employee selected");
	}

	public static void printNoActivitySelected() {
		System.out.println("No activity selected");
	}
		
}
