package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projectPlannerApp.Activity;
import projectPlannerApp.Employee;
import projectPlannerApp.InvalidTimeRegistrationException;
import projectPlannerApp.TimeRegistration;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class ActivityCalendar {
	
	private InvalidTimeRegistrationException invalidTimeRegistrationError = new InvalidTimeRegistrationException("Invalid time registration");

	private Calendar calendar = new GregorianCalendar();
      
	public final int YEAR = LocalDate.now().getYear();
	public final int MONTH = LocalDate.now().getMonthValue();
	public final int DAY = LocalDate.now().getDayOfMonth();
	public final int WEEKS = calendar.getActualMaximum(calendar.WEEK_OF_YEAR);
	
	private Map<Integer, Date> dates = new HashMap<Integer, Date>();
	private List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();

	private Activity activity;
	private Date startDate, endDate;
	
	public ActivityCalendar() {
		initCalendar();
	}
	
	public ActivityCalendar(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		addDate(startDate);
		addDate(endDate);
		initCalendar();
	}
	
	private void initCalendar() {
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(YEAR, MONTH, DAY);
	}
	
	public void setStart(Date date) {
		if (startDate != null) {
			dates.remove(startDate.getDateStamp());
		}
		startDate = date;	
		addDate(startDate);
	}
	
	public void setEnd(Date date) {
		if (endDate != null) {
			dates.remove(endDate.getDateStamp());
		}
		endDate = date;	
		addDate(endDate);
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public Map<Integer, Date> getDates() {
		return dates;
	}
	
	public Date getDate(int year, int month, int day) {
		Date date = new Date(year, month, day);
		if (this.contains(date)) {
			return dates.get(date.getDateStamp());
		}
		addDate(date);
		return date;
	}
	
	public Date getDate(int week) {
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
		LocalDate dateStart = LocalDate.ofYearDay(YEAR, 1).with(weekFields.weekOfYear(), week).with(weekFields.dayOfWeek(), 1);
		Date date = new Date(dateStart.getYear(), dateStart.getMonthValue(), dateStart.getDayOfMonth());
		
		if (date.isLEQ(getDate(calendar.YEAR, calendar.MONTH, calendar.DATE))) {
			dateStart = LocalDate.ofYearDay(YEAR+1, 1).with(weekFields.weekOfYear(), week).with(weekFields.dayOfWeek(), 1);
			date = new Date(dateStart.getYear(), dateStart.getMonthValue(), dateStart.getDayOfMonth());
		}
		if (this.contains(date)) {
			return dates.get(date.getDateStamp());
		}
		addDate(date);
		return date;
	}
	
	private List<Date> getDateRange(Date start, Date end) {
		List<Date> dateRange = new ArrayList<Date>();
		dateRange.add(start);
		
		calendar.set(start.getYear(), start.getMonth(), start.getDay());
		Date currDate = start;
		
		while (currDate.isLessThan(end)) {
			calendar.roll(DAY, true);
			currDate = getDate(calendar.YEAR, calendar.MONTH, calendar.DATE);
			dateRange.add(currDate);
		}
		
		dateRange.add(end);
		
		return dateRange;
	}
	
	private void addDate(Date date) {
		dates.put(date.getDateStamp(), date);
	}
	
	public TimeRegistration getTimeRegistration(Date date, Employee employee) {
		return date.getTimeRegistration(employee);
	}
	
	public List<TimeRegistration> getTimeRegistrations() {
		return timeRegistrations;
	}
	
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double hours) throws InvalidTimeRegistrationException {
		if (hours < 0 || hours > 24) {//1
			throw invalidTimeRegistrationError;
		}
		
		TimeRegistration registration = new TimeRegistration(date, employee, hours);
		
		if (timeRegistrations.size() > 0) {//2
			timeRegistrations.add(registration);
			sort();
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}

	
	public TimeRegistration newTimeRegistration(Date start, Date end, Employee employee) {
		TimeRegistration registration = new TimeRegistration(getDateRange(start, end), employee);
		
		if (timeRegistrations.size() > 0) {
			timeRegistrations.add(registration);
			sort();
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}

	private void sort() {
		int index = timeRegistrations.size()-1;
		while (timeRegistrations.get(index).getDate().isLEQ(timeRegistrations.get(index-1).getDate())) {
			Collections.swap(timeRegistrations, index-1, index);
			if (index == 1) {
				return;
			}	
			index--;
		}
	}
	
	public boolean contains(Date date) {
		if (dates.containsKey(date.getDateStamp())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Calendar for activity " + activity.getName();
	}
}
