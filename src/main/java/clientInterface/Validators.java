package clientInterface;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validators extends ClientInterface {
	
	/*
	 * This class contains the methods used for validating a user input
	 */
		
	public static int numberValidator(int range) {
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
	    	System.out.println("Enter initials in a length of 4 letters");
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

}
