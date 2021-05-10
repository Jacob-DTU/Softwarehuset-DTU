package clientInterface;

import projectPlannerApp.*;
import projectPlannerCalendar.*;

import java.util.ArrayList;


public class Modifiers extends ClientInterface {

	/*
	 * This class contains all methods used by the client to modify or add to the application
	 */

	public static void createEmployee() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Create Employee");
		Options.printSectionLine();

		initials = Validators.stringValidator("initials");
		try {
			employee = app.newEmployee(initials);
			showEmployee();
		}
		catch(OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void createProject() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Create Project");
		Options.printCreateProject();

		selector = Validators.rangeValidator(4, "Input");
		switch(selector) {
			case 0: // go back
				break;

			case 1: // Only name
				name = Validators.stringValidator("name");
				project = app.newProject(name);

				showProject();
				break;

			case 2: //Name and starting date
				name = Validators.stringValidator("name");
				date = Validators.dateValidator("project");
				project = app.newProject(name, date);

				showProject();
				break;

			case 3: //Name and project lead
				name = Validators.stringValidator("name");
				employees = app.searchEmployees(Validators.stringValidator("search"));
				showEmployeeOverview();
				project = app.newProject(name, employee);

				showProject();
				break;

			case 4: //Name and project lead and starting date
				name = Validators.stringValidator("name");
				employees = app.searchEmployees(Validators.stringValidator("search"));
				showEmployeeOverview();
				date = Validators.dateValidator("project");
				project = app.newProject(name, employee, date);

				showProject();
				break;
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void createActivity() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Create Activity");

		try {
			if (!project.hasProjectLead()) {
				project.newActivity(client, "name");
			}
			else if (!project.getProjectLead().equals(client)) {
				project.newActivity(client, "name");
			}
		} catch(ProjectLeadException e) {
			System.out.println("\n" + e.getMessage());
			Options.subpaths.remove(Options.subpaths.size()-1);
			return;
		}
		
		Options.printCreateActivity();
		
		selector = Validators.rangeValidator(2, "Input");
		switch(selector) {
			case 0: // cancel
				break;

			case 1: // Only name
				name = Validators.stringValidator("name");
				activity = project.newActivity(client, name);
				showActivity();
				break;

			case 2: // Name, start, end and duration
				name = Validators.stringValidator("name");
				int start = Validators.rangeValidator(app.getCalendar().WEEKS, "Start week");
				int end = Validators.rangeValidator(app.getCalendar().WEEKS, "End week");
				System.out.println("\nEnter a duration");
				int duration = Validators.getValidInt("Input");
				activity = project.newActivity(client, name, start, end, duration);
				showActivity();
				break;
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void createTimeRegistration() throws InvalidTimeRegistrationException, OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->Create Time Registration");
		Options.printSectionLine();
		
		if (activity.isPredefined) {			
			Date start = Validators.dateValidator("registration");
			Date end = Validators.dateValidator("registration");
			registration = activity.newTimeRegistration(start, end, client);
			showTimeRegistration();
		}
		else {			
			date = Validators.dateValidator("registration");
			double hours = Validators.hoursValidator();
			registration = activity.newTimeRegistration(date, client, hours);
			showTimeRegistration();
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void changeProjectName() throws OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->Change Project Name");
		Options.printSectionLine();
		
		name = Validators.stringValidator("name");
		project.setName(name);
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void changeProjectStart() {
		Options.subpaths.add("->Change Project Start");
		Options.printSectionLine();
		
		date = Validators.dateValidator("project");
		project.setProjectStart(date);
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void changeActivityName() throws OperationNotAllowedException, ProjectLeadException {
		Options.subpaths.add("->Change Activity Name");
		Options.printSectionLine();

		name = Validators.stringValidator("name");
		activity.setName("name");
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	private static void changeActivityStart() {
		int newStart = Validators.rangeValidator(app.getCalendar().WEEKS, "Start week");
		activity.setStart(newStart);
	}

	private static void changeActivityEnd() {
		int newEnd = Validators.rangeValidator(app.getCalendar().WEEKS, "End week");
		activity.setEnd(newEnd);
	}

	private static void changeActivityDuration() {
		Options.printChangeActivityDuration();

		int newDuration = Validators.getValidInt("Duration");
		activity.setDuration(newDuration);
	}

	public static void changeActivityTimeFrame() {
		Options.subpaths.add("->Change Activity Time Frame");
		Options.printSectionLine();

		changeActivityStart();
		changeActivityEnd();
		changeActivityDuration();
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void changeTimeRegistrationHours() {
		Options.subpaths.add("->Change Time Registration Hours");
		Options.printSectionLine();

		double newHours = Validators.hoursValidator();
		registration.setHours(newHours);
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void changeTimeRegistrationDate() {
		Options.subpaths.add("->Change Time Registration Date");
		Options.printSectionLine();

		date = Validators.dateValidator("registration");
		registration.setDate(date);
		Options.subpaths.remove(Options.subpaths.size()-1);
	}

	public static void setProjectLead() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Set Project Lead");
		Options.printSectionLine();

		initials = Validators.stringValidator("search");
		employees = app.searchEmployees(initials);

		showEmployeeOverview();
		if(employee != null) {
			try{
				project.setProjectLead(employee);
			}
			catch(ProjectLeadException e){
				System.out.println("\n" + e.getMessage());
			}
		}
		else {
			Options.printNoEmployeeSelected();
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}
	
	public static void addEmployee(Activity act) throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Add Employee");
		Options.printSectionLine();

		initials = Validators.stringValidator("search");
		employees = app.searchEmployees(initials);
		showEmployeeOverview();
		
		if(employee != null){
			try {
				if(!act.getEmployees().contains(employee)){
					act.addEmployee(employee);
				}
				else{
					Options.printActivityContainsEmployee();
				}
				
			} catch (TooManyActivitiesException e) {
				System.out.println(e.getMessage());
			}
		}
		else{
			Options.printNoEmployeeSelected();
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}
	
	public static void removeEmployee(Activity act) throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Remove employee");
		Options.printSectionLine();

		initials = Validators.stringValidator("search");
		employees = app.searchEmployees(initials);
		showEmployeeOverview();
		
		if(employee != null){
			act.removeEmployee(employee);
		}
		else {
			Options.printNoEmployeeSelected();
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}
	
	public static void removeActivity() throws OperationNotAllowedException, ProjectLeadException, InvalidTimeRegistrationException {
		Options.subpaths.add("->Remove Activity");

		activities = new ArrayList(project.getActivities().values());
		showActivityOverview();
		if(activity != null) {
			project.removeActivity(activity);
		}
		else{
			Options.printNoActivitySelected();
		}
		Options.subpaths.remove(Options.subpaths.size()-1);
	}
}
