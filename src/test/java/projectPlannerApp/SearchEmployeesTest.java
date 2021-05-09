package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SearchEmployeesTest {
	private HashMap<String,Employee> map = new HashMap<String,Employee>();

	@BeforeEach
    void init() {
		HashMap<String,Employee> map = new HashMap<String,Employee>();
    }

	@Test
	void testA() {
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.isEmpty());
	}

	@Test
	void testB() {
		Employee one = new Employee("ABCD");
		map.put(one.getInitials(), one);
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.isEmpty());
	}

	@Test
	void testC() {
		Employee one = new Employee("DECD");
		map.put(one.getInitials(), one);
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.size()== 1);
		assertEquals(found.get(0).getInitials(),"DECD");
	}

	@Test
	void testD() {
		Employee one = new Employee("ABDE");
		Employee two = new Employee("DFGH");
		map.put(one.getInitials(), one);
		map.put(two.getInitials(), two);
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.size()== 1);
		assertEquals(found.get(0).getInitials(),"ABDE");
	}

	@Test
	void testE() {
		Employee one = new Employee("CDFG");
		Employee two = new Employee("BDEF");
		Employee three = new Employee("DEAB");
		map.put(one.getInitials(), one);
		map.put(two.getInitials(), two);
		map.put(three.getInitials(), three);
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.size()== 2);
		assertEquals(found.get(0).getInitials(),"BDEF");
		assertEquals(found.get(1).getInitials(),"DEAB");
	}

	@Test
	void testF() {
		Employee one = new Employee("GDEA");
		Employee two = new Employee("VETZ");
		Employee three = new Employee("NODE");
		map.put(one.getInitials(), one);
		map.put(two.getInitials(), two);
		map.put(three.getInitials(), three);
		List<Employee> found = Search.searchEmployees(map, "DE");
		assertTrue(found.size()== 2);
		assertEquals(found.get(1).getInitials(),"GDEA");
		assertEquals(found.get(0).getInitials(),"NODE");
	}

}
