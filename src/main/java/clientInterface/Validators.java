package clientInterface;

import java.time.YearMonth;
import java.util.regex.Pattern;

import projectPlannerApp.OperationNotAllowedException;
import projectPlannerApp.ProjectLeadException;
import projectPlannerCalendar.Date;

public class Validators extends ClientInterface {
	
	/*
	 * This class contains the methods used for validating a user input
	 */
		
	public static int rangeValidator(int range, String input) {
		if (range == 0) {
		    System.out.println("\nInput 0 to go back");
		}
		else {
			System.out.println("\nInput a number between 0 and " + range);
		}
		
	    selector = getValidInt(input);
	    while (selector > range || selector < 0) {
            System.out.println("Invalid number: It must be a number between 0 and " + range );
            selector = getValidInt(input);
	    }
	    
		return selector;
    }
	
	public static double hoursValidator() {
		System.out.println("\nInput hours between 0 and 24");
		
	    double hours = getValidDouble("Input");
	    while (hours > 24 || hours < 0 || 2*hours != (int) 2*hours) {
            System.out.println("Invalid hours: The time registration should be within 24 hours with an accuracy of half hours");
            hours = getValidDouble("Input");
	    }
	    
		return hours;
	}
	
    
    public static String stringValidator(String command) throws OperationNotAllowedException, ProjectLeadException {
    	String input = null;
    	    	
    	switch (command) {
    	
	    	case "initials": // Get valid employee initials
		    	System.out.println("\nEnter initials with a length of 4 letters");
		    	input = getValidString("Initials");
			    while (input.length() != 4) {
			    	System.out.println("Invalid initials: It must be a string of 4 letters");
			    	input = getValidString("Initials");
			    }
		    	
			    break; 
		    	
	    	case "search": //Get valid employee intitials thats partial
	    		System.out.println("\nSearch for initials with up to 4 letters");
		    	input = getValidString("Search for");
		    	while (input.length() > 4) {
		    		System.out.println("Invalid search: It must be a string with up to 4 letters");
		    		input = getValidString("Search for");
	    		}
		    	break;  
	
	        case "name":
	            System.out.println("\nEnter a string without any numbers");
	            input = getValidString("Name");
	            break;
		}

	    return input;
    }
    
    public static Date dateValidator(String command) {
    	int year = 0, month = 0, day = 0;
    	
    	System.out.println("\nEnter a date");
    	year = getValidInt("Year");
    	while (year < app.getCalendar().YEAR) {
    		System.out.println("Invalid year: It can not be a year earlier than " + app.getCalendar().YEAR);
    		year = getValidInt("Year");
    	}
    	
    	month = getValidInt("Month");
    	while (month < 1 || month > 12) {
    		System.out.println("Invalid month: It must be a number in range 1 to 12");
    		month = getValidInt("Month");
    	}
    	
    	day = getValidInt("Day");
    	int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
    	while (day < 1 || day > daysInMonth) {
    		System.out.println("Invalid day: It must be a number in range 1 to " + daysInMonth);
    		day = getValidInt("Day");
    	}
    	
    	switch (command) {
			case "project": // get date from app calendar
    			calendar = app.getCalendar();
				break;
				
    		case "registration": // get date from activity calendar
    			calendar = activity.getCalendar();
    			break;
    	}
    	
    	return calendar.getDate(year, month, day);
	}
    
    private static String getValidString(String inputName) {
		System.out.print(inputName + ": ");
    	String input = sc.next();
    	while (!sc.hasNextLine() || !Pattern.matches("[a-zA-Z]+", input)) {
    		System.out.println("Invalid input: It must be a string consisting of letters from A-Z");
    		System.out.print(inputName + ": ");
    		input = sc.next();
    	}
    	
    	return input;
    }
    
    public static int getValidInt(String inputName) {
		System.out.print(inputName + ": ");
		sc.nextLine();
    	while (!sc.hasNextInt()) {
    		System.out.println("Invalid input: It must be an integer");
			System.out.print(inputName + ": ");
    		sc.nextLine();
    	}
    	
    	return sc.nextInt();
    }
    
    public static double getValidDouble(String inputName) {
  		System.out.print(inputName + ": ");
  		sc.nextLine();
      	while (!sc.hasNextDouble()) {
      		System.out.println("Invalid input: It must be a number");
  			System.out.print(inputName + ": ");
      		sc.nextLine();
      	}
      	
      	return sc.nextInt();
      }

}
