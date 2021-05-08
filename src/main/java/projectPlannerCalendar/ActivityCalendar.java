package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projectPlannerApp.Employee;
import projectPlannerApp.InvalidTimeRegistrationException;
import projectPlannerApp.TimeRegistration;

import java.time.LocalDate;

public class ActivityCalendar {
	
	private InvalidTimeRegistrationException invalidTimeRegistrationError = new InvalidTimeRegistrationException("Invalid time registration");

	private Calendar calendar = new GregorianCalendar();
      
	public final int YEAR = LocalDate.now().getYear();
	public final int MONTH = LocalDate.now().getMonthValue();
	public final int DAY = LocalDate.now().getDayOfMonth();
	public final int WEEKS = calendar.getActualMaximum(calendar.WEEK_OF_YEAR);
	
	private Map<Integer, Date> dates = new HashMap<Integer, Date>();
	private List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();

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
	
	public Date getDate(int year, int month, int day) {
		Date date = new Date(year, month, day);
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
	
	public TimeRegistration newTimeRegistration(Date date, Employee employee, double hours) throws InvalidTimeRegistrationException {
		if (hours < 0 || hours > 24) {
			throw invalidTimeRegistrationError;
		}
		
		TimeRegistration registration = new TimeRegistration(date, employee, hours);
		
		if (timeRegistrations.size() > 1) {
			int index = sortInsert(timeRegistrations.size() / 2, registration);
			timeRegistrations.add(index, registration);
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}

	
	public TimeRegistration newTimeRegistration(Date start, Date end, Employee employee) {
		TimeRegistration registration = new TimeRegistration(getDateRange(start, end), employee);
		
		if (timeRegistrations.size() > 1) {
			int index = sortInsert(timeRegistrations.size() / 2, registration);
			timeRegistrations.add(index, registration);
		}
		else {
			timeRegistrations.add(registration);
		}
		
		return registration;
	}
	
	private int sortInsert(int index, TimeRegistration registration) {
		Date currDate = timeRegistrations.get(index).getDate();
		Date prevDate = timeRegistrations.get(index - 1).getDate();
		Date nextDate = timeRegistrations.get(index + 1).getDate();
		if (prevDate.isLEQ(registration.getDate()) && nextDate.isGEQ(registration.getDate())) {
			return index;
		}
		else if (currDate.isLessThan(registration.getDate())) {
			index += index / 2;
		}
		else if (currDate.isGreaterThan(registration.getDate())) {
			index /= 2;
		}
		return sortInsert(index, registration);
	}
	
	public boolean contains(Date date) {
		if (dates.containsKey(date.getDateStamp())) {
			return true;
		}
		return false;
	}
}
