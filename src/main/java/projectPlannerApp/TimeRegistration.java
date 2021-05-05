package projectPlannerApp;
import projectPlannerCalendar.Date;

public class TimeRegistration {
		
	public Date date;
	public Employee employee;
	public double hours;
	
	public TimeRegistration(Date date, Employee employee, double hours) {
		this.date = date;
		this.employee = employee;
		this.hours = hours;
		date.addTimeRegistration(this);
	}
	
	public TimeRegistration(Date startDate, Date endDate, Employee employee2) {
		
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
