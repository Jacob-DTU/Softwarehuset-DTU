package projectPlannerApp;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Search{

	public static List<Employee> searchEmployees(Map<String, Employee> employees, String initials){
		List<Employee> list = new ArrayList<Employee>();
		if(employees.size()!= 0){
			for (Employee e: employees.values()){
				if(e.getInitials().contains(initials)){
					list.add(e);	
				}
			}
	
		}
		return list;
	}
	
	public static boolean contains(List<Employee> employees, Employee employee) {
		for (Employee existingEmployee : employees) {
			if (existingEmployee.getInitials().equals(employee.getInitials()));
			return true;
		}
		return false;
	}
    


}