package projectPlannerApp;

public class Activity {
	
	private String name;
	private int start, end, duration;
	
	public Activity(String name, int start, int end, int duration) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.duration = duration;
	}
	
}
