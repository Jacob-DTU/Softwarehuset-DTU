package projectPlannerCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projectPlannerApp.Employee;
import projectPlannerApp.TimeRegistration;

import java.time.LocalDate;

public class ProjectPlannerCalendar {
	private Calendar calendar = new GregorianCalendar();
      
	public final int YEAR = LocalDate.now().getYear();
	public final int MONTH = LocalDate.now().getMonthValue();
	public final int DAY = LocalDate.now().getDayOfMonth();
	public final int WEEKS = calendar.getActualMaximum(calendar.WEEK_OF_YEAR);
	
	public Map<Integer, Date> dates = new HashMap<Integer, Date>();
	public List<TimeRegistration> timeRegistrations = new ArrayList<TimeRegistration>();

	
	public Date startDate, endDate;
	
	public ProjectPlannerCalendar() {
		initCalendar();
	}
	
	public ProjectPlannerCalendar(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		addDate(startDate);
		addDate(endDate);
		initCalendar();
	}
	
	public void initCalendar() {
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(YEAR, MONTH, DAY);
		
	}
	
	public boolean contains(Date date) {
		if (dates.containsKey(date.getDateStamp())) {
			return true;
		}
		return false;
	}
	
	public Date getDate(int year, int month, int day) {
		Date date = new Date(year, month, day);
		if (this.contains(date)) {
			return dates.get(date.getDateStamp());
		}
		addDate(date);
		return date;
	}
	
	private void addDate(Date date) {
		dates.put(date.getDateStamp(), date);
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
	
	public int sortInsert(int index, TimeRegistration registration) {
		Date currDate = timeRegistrations.get(index).date;
		Date prevDate = timeRegistrations.get(index - 1).date;
		Date nextDate = timeRegistrations.get(index + 1).date;
		if (prevDate.isLEQ(registration.date) && nextDate.isGEQ(registration.date)) {
			return index;
		}
		else if (currDate.isLessThan(registration.date)) {
			index += index / 2;
		}
		else if (currDate.isGreaterThan(registration.date)) {
			index /= 2;
		}
		return sortInsert(index, registration);
	}

	public void addTimeRegistration(TimeRegistration registration) {
		timeRegistrations.add(registration);
	}
	
	public List<Date> getDateRange(Date start, Date end) {
		List<Date> dateRange = new ArrayList<Date>();
		dateRange.add(start);
		
		calendar.set(start.year, start.month, start.day);
		Date currDate = start;
		
		while (currDate.isLessThan(end)) {
			calendar.roll(DAY, true);
			currDate = getDate(calendar.YEAR, calendar.MONTH, calendar.DATE);
			dateRange.add(currDate);
		}
		
		dateRange.add(end);
		
		return dateRange;
	}
	
}
