package clientInterface;

import projectPlannerApp.*;
import clientInterface.*;

public class Modifiers extends ClientInterface {
	
	/*
	 * This class contains all methods used by the client to modify or add to the application
	 */
	
	public static int selector;
	
	public static void createEmployee() {
    	
    }
    
    public static void createProject() {
    	Options.printCreateProject();
    	selector = Validators.numberValidator(4);
        switch(selector) {
        	case 0: // go back
        		showProjectPlannerApp();
            case 1:
                app.projects.add(app.newProject(Validators.stringValidator("name")));
                showProjectPlannerApp();
            case 2:
                app.projects.add(app.newProject(Validators.stringValidator("name"), year, month, day));
                showProjectPlannerApp();
            case 3: 
                app.projects.add(app.newProject(Validators.stringValidator("name"), client));
                showProjectPlannerApp();
            case 4: 
         }
    }
    
    public static void createActivity() {
    	
    }
    
    public static void createTimeRegistration() {
    	
    }
    
    public static void changeProjectName() throws OperationNotAllowedException, ProjectLeadException {
    	
        int projectNumber = Validators.numberValidator(app.getProjects().size());
        project = app.getProjects().get(projectNumber);
        project.setName(Validators.stringValidator("name"));
        System.out.println("New name is " + project.getName() + "Project number is " + project.getProjectNumber()); // Måske bare print project (project.toString)
        showProjectPlannerApp();
        
    }
    
    public static void changeProjectStart() {
    	
    }
    
    public static void changeActivityName() {
    	
    }
    
    public static void changeActivityStart() {
    	
    }
    
    public static void changeActivityEnd() {
    	
    }
    
    public static void changeActivityDuration() {
    	
    }
    
    public static void changeTimeRegistrationHours() {
    	
    }
    
    public static void changeTimeRegistrationDate() {
    	
    }
    
    public static void setProjectLead() {
    	
    }
}
