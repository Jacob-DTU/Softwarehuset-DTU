package clientInterface;

import projectPlannerApp.*;

import java.util.ArrayList;
import java.util.List;

import clientInterface.*;

public class Modifiers extends ClientInterface {
	
	/*
	 * This class contains all methods used by the client to modify or add to the application
	 */
		
	public static void createEmployee() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printCreateEmployee();
    	initials = Validators.stringValidator("initials");
    	employee = app.newEmployee(initials);
    	
    	showEmployee();
    	showProjectPlannerApp();
    }
    
    public static void createProject() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printCreateProject();
    	
    	selector = Validators.rangeValidator(4);
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
        showProjectPlannerApp();
    }
    
    public static void createActivity() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printCreateActivity();
    	
    	selector = Validators.rangeValidator(2);
    	switch(selector) {
    	case 0: // go to main menu
    		break;
    		
        case 1: // Only name
        	name = Validators.stringValidator("name");
            activity = project.newActivity(client, name);
            break;
            
        case 2: // Name, start, end and duration
        	name = Validators.stringValidator("name");
        	int start = Validators.rangeValidator(app.calendar.WEEKS);
        	int end = Validators.rangeValidator(app.calendar.WEEKS);
        	int duration = Validators.getValidInt("Input");
        	activity = project.newActivity(client, name, start, end, duration);
            break;
    	}    	
    	showProjectPlannerApp();
    }
    
    public static void createTimeRegistration() {
    	Options.printCreateTimeRegistration();
    	registration = activity.newTimeRegistration(date, employee, hours)
    }
    
    public static void changeProjectName() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printChangeProjectName();
        int projectNumber = Validators.rangeValidator(app.getProjects().size());
        project = app.getProjects().get(projectNumber);
        project.setName(Validators.stringValidator("name"));
        System.out.println("New name is " + project.getName() + "Project number is " + project.getProjectNumber()); // Måske bare print project (project.toString)
        showProjectPlannerApp();
        
    }
    
    public static void changeProjectStart() {
    	Options.printChangeProjectStart();
    	
    	date = Validators.dateValidator("project");
        project.setProjectStart(date);
    }
    
    public static void changeActivityName() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printChangeActivityName();
    	
    	name = Validators.stringValidator("name");
    	activity.setName("name");
    }
    
    public static void changeActivityStart() {
    	Options.printChangeActivityStart();
    	
    	int newStart = Validators.rangeValidator(app.calendar.WEEKS);
    	activity.setStart(newStart);
    }
    
    public static void changeActivityEnd() {
    	Options.printChangeActivityEnd();
    	
    	int newEnd = Validators.rangeValidator(app.calendar.WEEKS);
    	activity.setStart(newEnd);
    }
    
    public static void changeActivityDuration() {
    	Options.printChangeActivityDuration();

    	int newDuration = Validators.getValidInt("Input");
    	activity.setStart(newDuration);
    }
    
    public static void changeTimeRegistrationHours() {
    	Options.printChangeTimeRegistrationHours();
    	
    	int newEnd = Validators.getValidInt("Input");
    	activity.setStart(newEnd);
    }
    
    public static void changeTimeRegistrationDate() {
    	Options.printChangeTimeRegistrationDate();
    	
    	int newHours = Validators.getValidInt("Input");
    	registration.setHours(newHours);
    }
    
    public static void setProjectLead() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printSetProjectLead();
       
        initials = Validators.stringValidator("search");
        employees = app.searchEmployees(initials);
        
        Options.showEmployeeOverview();
		project.setProjectLead(employee);
		showProject();
    }
}
