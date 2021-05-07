package projectPlannerApp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class EmployeeTest {

	private Project project;
	private ProjectPlannerApp projectPlannerApp;
	private Employee projectLead;


	@Test
	void TestA() {
		ArrayList<Project> projects = new ArrayList();
		projects.add(project);
		assertEquals(project,projects.get(0));
	}

}
