package projectPlannerApp;

public class TooManyActivitiesException extends Exception {

	private static final long serialVersionUID = 112313L;
	
    public TooManyActivitiesException(String errorMessage){
        super(errorMessage);
    }
}
