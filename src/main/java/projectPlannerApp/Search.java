package projectPlannerApp;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Search{

	public static List<Employee> searchEmployees(Map<String, Employee> employees, String initials){
		assert (initials.length() <= 4 && employees != null) : "Precondition"; 
		List<Employee> list = new ArrayList<Employee>();
		if(employees.size()!= 0){//1
			for (Employee e: employees.values()){//2
				if(e.getInitials().contains(initials.toUpperCase())){//3
					list.add(e);	
				}
			}
		}
		assert (list != null && comparer(list,employees)): "Postcondition";
		return list;
	}
	
	//Design by contract only
	public static boolean comparer(List<Employee> sortedlist,Map<String,Employee> originallist) {
		if(sortedlist.size()==0) {
			return true;
		}
		if(Compair(sortedlist,originallist)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	//Design by contract only 
	public static boolean Compair(List<Employee> sortedlist,Map<String,Employee> originallist) {
		boolean exist = false;
		for(Employee sorted: sortedlist) {
innerloop: for(Employee original: originallist.values()) {
				if(sorted.getInitials().equals(original.getInitials())) {
					exist = true;
					break innerloop; //Speed so not O(n*k) always
				}
			}
			if(!exist) {
				return false;
			}
			exist = false; 
		}
		return true;
	}
	
	
	public static boolean contains(List<Employee> employees, Employee employee) {
		for (Employee existingEmployee : employees) {
			if (existingEmployee.getInitials().equals(employee.getInitials()));
			return true;
		}
		return false;
	}
    


}