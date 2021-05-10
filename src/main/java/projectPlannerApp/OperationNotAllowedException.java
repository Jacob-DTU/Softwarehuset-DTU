package projectPlannerApp;

public class OperationNotAllowedException extends Exception {

	/*
	* OperationNotAllowed Exception: Christopher
	*/
	
	private static final long serialVersionUID = 5644804383994321392L;

	public OperationNotAllowedException(String errorMessage) {
		super(errorMessage);
	}

}