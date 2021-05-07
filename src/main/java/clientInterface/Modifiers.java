package clientInterface;

import projectPlannerApp.*;

import java.util.ArrayList;
import java.util.List;

import clientInterface.*;

public class Modifiers extends ClientInterface {
	
	/*
	 * This class contains all methods used by the client to modify or add to the application
	 */
		
	public static void createEmployee() throws OperationNotAllowedException {
    	Options.printCreateEmployee();
    	initials = Validators.stringValidator("initials");
    	employee = app.newEmployee(initials);
    	showEmployee();
    }
    
    public static void createProject() throws OperationNotAllowedException, ProjectLeadException {
    	Options.printCreateProject();
    	
    	selector = Validators.rangeValidator(4);
        switch(selector) {
        	case 0: // go back
        		showProjectPlannerApp();
            case 1: // Only name
                app.getProjects().add(app.newProject(Validators.stringValidator("name")));
                showProjectPlannerApp();
            case 2: //Name and date
            	date = Validators.dateValidator();
                app.getProjects().add(app.newProject(Validators.stringValidator("name"), date));
                showProjectPlannerApp();
            case 3: //Name and projectlead
                app.getProjects().add(app.newProject(Validators.stringValidator("name"), client));
                showProjectPlannerApp();
            case 4: //Name and date and projectlead
                app.getProjects().add(app.newProject(Validators.stringValidator("name"), client, date));
         }
    }
    
    public static void createActivity() throws ProjectLeadException, OperationNotAllowedException {
    	Options.printCreateActivity();
    	
    	name = Validators.stringValidator("name");
    	
    	activity = project.newActivity(client, name);
    }
    
    public static void createTimeRegistration() {
    	Options.printCreateTimeRegistration();
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
        project.getProjectStart();
        project.setProjectStart();
    }
    
    public static void changeActivityName() {
    	Options.printChangeActivityName();
    }
    
    public static void changeActivityStart() {
    	Options.printChangeActivityStart();
    }
    
    public static void changeActivityEnd() {
    	Options.printChangeActivityEnd();

    }
    
    public static void changeActivityDuration() {
    	Options.printChangeActivityDuration();

    }
    
    public static void changeTimeRegistrationHours() {
    	Options.printChangeTimeRegistrationHours();

    }
    
    public static void changeTimeRegistrationDate() {
    	Options.printChangeTimeRegistrationDate();

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
