package projectPlannerCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class ProjectPlannerCalendar {
	Calendar calendar = new GregorianCalendar();
      
	public final int YEAR = LocalDate.now().getYear();
	public final int MONTH = LocalDate.now().getMonthValue();
	public final int DAY = LocalDate.now().getDayOfMonth();
	
	public Map<Integer, Date> dates = new HashMap<Integer, Date>();
	
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
	
}
