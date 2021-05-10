package projectPlannerApp;

public class InvalidTimeRegistrationException extends Exception {
	/*
	* InvalidTimeRegistration Exception: Simon
	*/
	private static final long serialVersionUID = 8113631698843461566L;
	
	public InvalidTimeRegistrationException(String errorMessage) {
		super(errorMessage);
	}

}
