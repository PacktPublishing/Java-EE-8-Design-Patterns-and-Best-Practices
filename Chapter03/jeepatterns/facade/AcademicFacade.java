package book.jeepatterns.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import book.jeepatterns.model.Course;
import book.jeepatterns.model.Discipline;
import book.jeepatterns.model.Professor;
import book.jeepatterns.model.Student;

public interface AcademicFacade {
	
	List<Discipline>getDisciplinesByCourse (Course course);
	List<Discipline>getPreRequisiteDisciplines (Discipline discipline);
	List<Discipline>getDisciplinesByProfessor (Professor professor);
	List<String> getBooksByDiscipline (Discipline discipline);
	
	List<Student>getEnrolledStudentByCourse (Course course);
	List<Student>getApprovedStudentByDisciplineAndYearAndPeriod (Course course, int year, int period);
	void requestTestReview (Student student, Discipline discipline, LocalDate testDate);
	
	List<Professor> getProfessorsByDiscipline (Discipline discipline);
	Set<Professor> getProfessorsByCourse (Course course);
}
