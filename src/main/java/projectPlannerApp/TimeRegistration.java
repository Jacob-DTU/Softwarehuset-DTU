package projectPlannerApp;
import java.util.List;

import projectPlannerCalendar.Date;

public class TimeRegistration {
		
	private Date date;
	private Employee employee;
	private double hours;
	
	public TimeRegistration(Date date, Employee employee, double hours) {
		this.date = date;
		this.employee = employee;
		this.hours = hours;
		date.addTimeRegistration(this);
	}
	
	public TimeRegistration(List<Date> dateRange, Employee employee) {
		for (Date date : dateRange) {
			this.date = date;
			this.employee = employee;
			this.hours = 24;
			date.addTimeRegistration(this);
		}
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date.getTimeRegistrations().remove(this);
		this.date = date;
		date.addTimeRegistration(this);
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public double getHours() {
		return hours;
	}
	
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public String toString() {
		return date.toString() + "\t" + employee.getInitials() + "\t" + Double.toString(hours);
	}
	
}
