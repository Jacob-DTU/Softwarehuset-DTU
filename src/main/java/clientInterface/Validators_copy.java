package clientInterface;

import java.time.YearMonth;
import java.util.Scanner;
import java.util.regex.Pattern;

import projectPlannerCalendar.Date;

public class Validators extends ClientInterface {
	
	/*
	 * This class contains the methods used for validating a user input
	 */
		
	public static int rangeValidator(int range) {
	    System.out.println("Press a number between 0 and " + range);
	    numberloop: while(true) {
		    if(sc.hasNextInt()) {
		        selector = sc.nextInt();
		        
		        if(selector > range || selector < 0){
		            System.out.println("Not a number between 0 and " + range );
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
		return selector;
    }
    
    public static String stringValidator(String command) {
    	String input = null;
    	
    	switch (command) {
    	
    	case "initials": // Get valid employee initials
	    	System.out.println("Enter initials with a length of 4 letters");
	    	if (sc.hasNextLine()) {
	    		input = sc.nextLine();
	    	
	    		while (input.length() != 4  || !Pattern.matches("[a-zA-Z]+", input)) {
	    			System.out.println("Invalid initials, try again");
	    			if (sc.hasNextLine()) {
	    				input = sc.nextLine();
	    			}
	    		}
    		}
	    	break; 
	    	
    	case "search": //Get valid employee intitials thats partial
    		System.out.println("Search for initials with up to 4 letters");
	    	if (sc.hasNextLine()) {
	    		input = sc.nextLine();
	    	
	    		while (input.length() <=4  || !Pattern.matches("[a-zA-Z]+", input)) {
	    			System.out.println("Invalid initials or length, try again");
	    			if (sc.hasNextLine()) {
	    				input = sc.nextLine();
	    			}
	    		}
    		}
	    	break;  

        case "name":
            System.out.println("Enter a string without any numbers");
            if (sc.hasNextLine()) {
	    		input = sc.nextLine();
	    	
	    		while (!Pattern.matches("[a-zA-Z]+", input)) {
	    			System.out.println("Invalid name, try again");
	    			if (sc.hasNextLine()) {
	    				input = sc.nextLine();
	    			}
	    		}
    		}
            break;
		}

        return input;
    }
    
    public static Date dateValidator() {
    	int year = 0, month = 0, day = 0;
    	
    	System.out.println("Enter a date");
    	System.out.print("Year: ");
    	if (sc.hasNextInt()) {
			year = sc.nextInt();
    	}
    	while (sc.hasNextInt() && year < app.calendar.YEAR) {
    		System.out.println("Not an integer or year earlier than this year");
    		year = sc.nextInt();
    	
    	}
    	System.out.print("Month: ");
    	while (month < 1 || month > 12) {
    		if (sc.hasNextInt()) {
    			month = sc.nextInt();
    		}
    	}
    	System.out.print("Day: ");
    	while (day < 1 || day > YearMonth.of(year, month).lengthOfMonth()) {
    		if (sc.hasNextInt()) {
    			day = sc.nextInt();
    		}
    	}
    	
    	
    	return date;
	}
    
    private static int getValidInt() {
    	while (!sc.hasNextInt()) {
    		sc.next();
    	}
    	
    	return sc.;
    }

}
