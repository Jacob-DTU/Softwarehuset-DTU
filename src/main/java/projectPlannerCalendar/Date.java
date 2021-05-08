package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.List;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

public class Date {
	
	private int year;
	private int month;
	private int day;
	
	private List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();
	
	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public List<TimeRegistration> getTimeRegistrations() {
		return timeRegistrations;
	}

	public int getDateStamp() {
		return getYear()*10000 + getMonth()*100 + getDay();
	}
	
	public void addTimeRegistration(TimeRegistration registration) {
		getTimeRegistrations().add(registration);
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

	public boolean isLEQ(Date date) {
		if (getDateStamp() <= date.getDateStamp()) {
			return true;
		}
		return false;
	}
	
	public boolean isGEQ(Date date) {
		if (getDateStamp() >= date.getDateStamp()) {
			return true;
		}
		return false;
	}
	
	public boolean contains(TimeRegistration registration) {
		if (getTimeRegistrations().contains(registration)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String dayString = Integer.toString(getDay());
		String monthString = Integer.toString(getMonth());
		String yearString = Integer.toString(getYear());
		if (dayString.length() < 2) {
			dayString = "0" + dayString;
		}
		if (monthString.length() < 2) {
			monthString = "0" + monthString;
		}
		
		return  dayString + "/" + monthString + "/" + yearString;
	}
	
}