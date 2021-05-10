package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.List;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

public class Date {

	/*
	 * Date Objects: Jacob
	 */
	
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
	public void setYear(int year) {
		this.year= year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setDay(int day) {
		this.day = day;
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
	
	public TimeRegistration getTimeRegistration(Employee employee) {
		for (TimeRegistration reg : timeRegistrations) {
			if (reg.getEmployee().equals(employee)) {
				return reg;
			}
		}
		return null;
	}

	public int getDateStamp() {
		return year*10000 + month*100 + day;
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
	
}