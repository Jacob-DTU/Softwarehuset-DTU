package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.List;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

public class Date {
	
	public int year;
	public int month;
	public int day;
	
	public List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();
	
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getDateStamp() {
		return year*10000 + month*100 + day;
	}
	
	public String toString() {
		String dayString = Integer.toString(day);
		String monthString = Integer.toString(month);
		String yearString = Integer.toString(year);
		if (dayString.length() < 2) {
			dayString = "0" + dayString;
		}
		if (monthString.length() < 2) {
			monthString = "0" + monthString;
		}
		
		return  dayString + "/" + monthString + "/" + yearString;
	}
	
	public void addTimeRegistration(TimeRegistration registration) {
		timeRegistrations.add(registration);
	}

	public boolean isLessThan(Date date) {
		if (getDateStamp() < date.getDateStamp()) {
			return true;
		}
		return false;
	}

	public boolean isGreaterThan(Date date) {
		if (getDateStamp() > date.getDateStamp()) {
			return true;
		}
		return false;
	}

	public boolean isLEQThan(Date date) {
		if (getDateStamp() <= date.getDateStamp()) {
			return true;
		}
		return false;
	}
	
	public boolean isGEQThan(Date date) {
		if (getDateStamp() >= date.getDateStamp()) {
			return true;
		}
		return false;
	}
	
	public boolean contains(TimeRegistration registration) {
		if (timeRegistrations.contains(registration)) {
			return true;
		}
		return false;
	}
}