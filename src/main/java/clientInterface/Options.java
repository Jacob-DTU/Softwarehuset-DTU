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
	
	public static void printMain() {
		System.out.println("\n 0. Close program");
		System.out.println("1. Login");
	}
	
	public static void printProjectPlannerApp() {
		System.out.println("\n 0. Switch user");
		System.out.println(" 1. View employees");
    	System.out.println(" 2. View projects");
    	System.out.println(" 3. View global calendar");
    	System.out.println(" 4. Create new project");
		System.out.println(" 5. Create new Employee");
	}
	
	public static void printEmployeeOverview() {
		printCloseConfirm();
		System.out.println("1. Search employee");
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
        for (Project project : app.getProjects()) {
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
	
	public static void printCalendar(ActivityCalendar calendar) {
		printCloseConfirm();
		System.out.println(calendar.toString());
	}
	
	public static void printEmployee(Employee employee) {
		printCloseConfirm();
		System.out.println(employee.toString());
	}
	
	public static void printProject(Project project) {
		printCloseConfirm();
		System.out.println(project.toString());
	}
	
	public static void printActivity(Activity activity) {
		printCloseConfirm();
		System.out.println(activity.toString());
	}
	
	public static void printDate(Date date) {
		printCloseConfirm();
		System.out.println(date.toString());
	}
	
	public static void printTimeRegistration(TimeRegistration registration) {
		printCloseConfirm();
		System.out.println(registration.toString());
	}
	
	public static void printCreateEmployee() {
		printCloseConfirm();

		
	}
	
	public static void printCreateProject() {
		printCloseConfirm();

		System.out.println(" 1. Make a new project with a name");
        System.out.println(" 2. Make a new project with a name and starting date");
        System.out.println(" 3. Make a new project with a name and project lead");
        System.out.println(" 4. Make a new project with a name and project lead and starting date");
	}
	
	public static void printCreateActivity() {
		printCloseConfirm();

	}
	
	public static void printCreateTimeRegistration() {
		printCloseConfirm();

	}
	
	public static void printChangeProjectName() {
		printCloseConfirm();

	}
	
	public static void printChangeProjectStart() {
		printCloseConfirm();

	}
	
	public static void printChangeActivityName() {
		printCloseConfirm();

	}
	
	public static void printChangeActivityStart() {
		printCloseConfirm();

	}
	
	public static void printChangeActivityEnd() {
		printCloseConfirm();

	}
	
	public static void printChangeActivityDuration() {
		printCloseConfirm();

	}
	
	public static void printChangeTimeRegistrationHours() {
		printCloseConfirm();

	}
	
	public static void printChangeTimeRegistrationDate() {
		printCloseConfirm();

	}
	
	public static void printSetProjectLead() {
		System.out.println("Please input initials for an existing employee");
		printCloseConfirm();

	}

}
