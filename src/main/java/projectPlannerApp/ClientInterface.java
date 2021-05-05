package projectPlannerApp;

import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.print.attribute.IntegerSyntax;



public class ClientInterface {
	
	public static final Scanner sc = new Scanner(System.in);
	public static final ProjectPlannerApp app = new ProjectPlannerApp();
	
	public static List<Project> projects = new ArrayList<Project>();
	public static Employee client;
	
    public static void main(String[] args) throws OperationNotAllowedException {
    	showLogin();
    }
    
    public static int numberValidator(int range) {
	    int number;
	    System.out.println("Press a number between 1 and " + range);
	    numberloop: while(true) {
		    if(sc.hasNextInt()) {
		        number=sc.nextInt();
		        
		        if(number>range || number<1){
		            System.out.println("Not a number between 1 and " + range );
		            sc.next();
		        }
		        else{
		            break numberloop;
		        }
		       
		    }
		    else {
		        System.out.println("You did not enter a number");
		        sc.next();
		    }
	    }
		return number;
    }
    
    public static String stringValidator(String command) {
    	String input = null;
    	
    	switch (command) {
    	
    	case "initials": // Get valid employee initials
	    	System.out.println("Enter initials in a length of 4 letters");
	    	if (sc.hasNextLine()) {
	    		input = sc.nextLine();
	    	
	    		while (input.length() != 4  || !Pattern.matches("[a-zA-Z]+", input)) {
	    			System.out.println("Invalid initials");
	    			if (sc.hasNextLine()) {
	    				input = sc.nextLine();
	    			}
	    		}
    		}
	    	break;    		
    		
    	}
		return input;
    }
    
    public static void showLogin() throws OperationNotAllowedException {
    	String initials = stringValidator("initials");
    	
		client = app.getEmployee(initials.toUpperCase());
		showProjectPlannerApp();
    }
    
    public static void showProjectPlannerApp() {
    	System.out.println(" 1. View projects");
    	System.out.println(" 2. View employees");
    	System.out.println(" 3. View global calendar");
        System.out.println(" 4. Switch user");    
        
        int number;
        number = numberValidator(4);
        switch(number) {
            //View projects
         case 1:
            projects = app.getProjectList();
            for(Project project : projects){
               System.out.println(project.toString()); 
            }
            System.out.println("1. Go back");
            System.out.println("2. Search Project");
            System.out.println("3. Change Name for project");
            System.out.println("4. Change starting date project");
            int number2;
            number2 = numberValidator(4);
            switch(number2){
                //Go back
                case 1:
                    showProjectPlannerApp();
                //Search Projects
                case 2: 
                //Change name for Project
                case 3:
                //Change starting date for project
                case 4:

            }

             break;
            //View employees
         case 2:
            // code block
             break;
             //View global calendar
        case 3:
            // code block
            break;
            //Switch user
        case 4: 
            // code block
            break; 
        }
        
    }
    
	
}
