package clientInterface;

import projectPlannerApp.*;
import projectPlannerCalendar.*;

public class Options extends ClientInterface {
	
	/*
	 * This class contains all visual parts of the interface
	 */
	
	public static void printToMainMenu() {
		System.out.println(" 0. Go to main menu");
	}
	
	public static void printLogin() {
		System.out.println(" 0. Switch user");
	}
	
	public static void printProjectPlannerApp() {
		printLogin();
		System.out.println(" 1. View employees");
    	System.out.println(" 2. View projects");
    	System.out.println(" 3. View global calendar");
    	System.out.println(" 4. Create new project");
	}
	
	public static void printEmployeeOverview() {
		printToMainMenu();

	}
	
	public static void printProjectOverview() {
		printToMainMenu();

    	int i = 2;
        for(Project project : app.getProjects()) {
            System.out.println(i++ + ". " + project.toString());
        }
	}
	
	public static void printActivityOverview() {
		printToMainMenu();

	}
	
	public static void printCalendar(ProjectPlannerCalendar calendar) {
		printToMainMenu();
		System.out.println(calendar.toString());
	}
	
	public static void printEmployee(Employee employee) {
		printToMainMenu();
		System.out.println(employee.toString());
	}
	
	public static void printProject(Project project) {
		printToMainMenu();
		System.out.println(project.toString());
	}
	
	public static void printActivity(Activity activity) {
		printToMainMenu();
		System.out.println(activity.toString());
	}
	
	public static void printDate(Date date) {
		printToMainMenu();
		System.out.println(date.toString());
	}
	
	public static void printTimeRegistration(TimeRegistration registration) {
		printToMainMenu();
		System.out.println(registration.toString());
	}
	
	public static void printCreateEmployee() {
		printToMainMenu();

		
	}
	
	public static void printCreateProject() {
		printToMainMenu();

		System.out.println("1. Make a new project with a name");
        System.out.println("2. Make a new project with a name and startDate");
        System.out.println("3. Make a new project with a name and projectLead");
        System.out.println("4. Make a new project with a name and projectLead and startDate");
	}
	
	public static void printCreateActivity() {
		printToMainMenu();

	}
	
	public static void printCreateTimeRegistration() {
		printToMainMenu();

	}
	
	public static void printChangeProjectName() {
		printToMainMenu();

	}
	
	public static void printChangeProjectStart() {
		printToMainMenu();

	}
	
	public static void printChangeActivityName() {
		printToMainMenu();

	}
	
	public static void printChangeActivityStart() {
		printToMainMenu();

	}
	
	public static void printChangeActivityEnd() {
		printToMainMenu();

	}
	
	public static void printChangeActivityDuration() {
		printToMainMenu();

	}
	
	public static void printChangeTimeRegistrationHours() {
		printToMainMenu();

	}
	
	public static void printChangeTimeRegistrationDate() {
		printToMainMenu();

	}
	
	public static void printSetProjectLead() {
		printToMainMenu();

	}

}