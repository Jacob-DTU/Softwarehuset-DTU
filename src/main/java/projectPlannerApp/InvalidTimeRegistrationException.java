package projectPlannerApp;

public class InvalidTimeRegistrationException extends Exception {
	
	private static final long serialVersionUID = 8113631698843461566L;
	
	public InvalidTimeRegistrationException(String errorMessage) {
		super(errorMessage);
	}

}
