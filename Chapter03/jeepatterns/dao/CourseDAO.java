package book.jeepatterns.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.jeepatterns.model.Course;
import book.jeepatterns.model.Discipline;

public class CourseDAO {
	private static Map<Course, List<Discipline>> courseXDisciplines;
	static {
		Discipline d1 = new Discipline("D1", "discipline 1");
		Discipline d2 = new Discipline("D2", "discipline 2");
		Discipline d3 = new Discipline("D3", "discipline 3");
		Discipline d4 = new Discipline("D4", "discipline 4");
		
		courseXDisciplines = new HashMap<>();
		courseXDisciplines.put (new Course ("C01", "Course 1"), Arrays.asList (d1, d2, d4));
		courseXDisciplines.put (new Course ("C02", "Course 2"), Arrays.asList (d1, d3));
		courseXDisciplines.put (new Course ("C03", "Course 3"), Arrays.asList (d2, d3, d4));
	}
	
	public List<Discipline> getDisciplinesByCourse(Course course) {
		Course courseAux = courseXDisciplines.keySet()
		.stream()
		.filter(c->c.getCode().equals(course.getCode()))
		.findFirst()
		.get();
		return courseXDisciplines.get (courseAux);
		//return courseXDisciplines.get(course);
	}
	
}
