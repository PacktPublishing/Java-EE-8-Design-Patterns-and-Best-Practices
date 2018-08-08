package book.jeepatterns.facade;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import book.jeepatterns.bo.ProfessorBO;
import book.jeepatterns.dao.CourseDAO;
import book.jeepatterns.dao.DisciplineDAO;
import book.jeepatterns.dao.ProfessorDAO;
import book.jeepatterns.dao.StudentDAO;
import book.jeepatterns.interceptor.Statistical;
import book.jeepatterns.model.Course;
import book.jeepatterns.model.Discipline;
import book.jeepatterns.model.Professor;
import book.jeepatterns.model.Student;
import book.jeepatterns.model.TestRevisionTO;

/**
 * Session Bean implementation class AcademicFacadeImpl
 */
@Stateless
@LocalBean 
public class AcademicFacadeImpl implements AcademicFacadeRemote, AcademicFacadeLocal {
	
	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private DisciplineDAO disciplineDAO;
	
	@Inject
	private StudentDAO studentDAO;
	
	@Inject
	private ProfessorDAO professorDAO;   
	
	@Inject 
	private ProfessorBO professorBO;

	@Override
	public List<Discipline> getDisciplinesByCourse(Course course) {
		return courseDAO.getDisciplinesByCourse (course);
	}

	@Override
	public List<Discipline> getPreRequisiteDisciplines (Discipline discipline) {
		return disciplineDAO.getPreRequisiteDisciplines(discipline);
	}

	@Override
	public List<Discipline> getDisciplinesByProfessor(Professor professor) {
		return disciplineDAO.getDisciplinesByProfessor(professor);
	}

	@Override
	public List<String> getBooksByDiscipline(Discipline discipline) {
		return disciplineDAO.getBooksByDiscipline(discipline);
	}

	@Override
	public List<Student> getEnrolledStudentByCourse(Course course) {
		return studentDAO.getEnrolledStudentByCourse (course);
	}

	@Override
	public List<Student> getApprovedStudentByDisciplineAndYearAndPeriod(Course course, int year, int period) {
		// TODO 
		return null;
	}
	
	@Override
	public void requestTestReview (Student student, Discipline discipline, LocalDate testDate) {
		// TODO
	}
	
	private LocalDateTime scheduleTestReview (TestRevisionTO testRevisionTO)
	{
		LocalDateTime dateTime = null;
		try {
			Thread.sleep(10000); 
			// get some code to calculate the schedule date for the test review
			dateTime = LocalDateTime.now().plusDays(10);
			if (dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				dateTime = dateTime.plusDays(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	private void sendEmail (TestRevisionTO testRevisionTO, LocalDateTime dateTime) {
		Student student = studentDAO.getStudentByEnrollment (testRevisionTO.getEnrollment());
		String enrollment = student.getEnrollment(); //testRevisionTO.getEnrollment()
		String studentName = student.getName();
		String email = student.getEmail();
		Discipline discipline = disciplineDAO.getDisciplineByCode (testRevisionTO.getDisciplineCode());
		String disciplineName = discipline.getName(); 
		String disciplineCode = discipline.getCode(); // testRevisionTO.getDisciplineCode()
		String date  = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String time  = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		// sending an email using the above information ...
		System.out.println("sending an email to : " + studentName + " ...");
	}
	
	@Statistical
	public void requestTestReview (@Observes TestRevisionTO testRevisionTO) {
	//public void requestTestReview (@ObservesAsync TestRevisionTO testRevisionTO) {  // the observer must be qualified as @ObservesAsync. It is new in JEE8...  
		System.out.println("matricula " + testRevisionTO.getEnrollment());
		LocalDateTime dateTime = scheduleTestReview (testRevisionTO);
		sendEmail (testRevisionTO, dateTime); // send an email with the schedule date for the test review:
	}

	@Override
	public List<Professor> getProfessorsByDiscipline(Discipline discipline) {
		return disciplineDAO.getProfessorByDiscipline(discipline);
	}

	@Override
	public Set<Professor> getProfessorsByCourse(Course course) {
		
		return null;
	}
	
	public boolean canProfessorTeachDiscipline (Professor professor, Discipline discipline) {
		/*return disciplineDAO.getDisciplinesByProfessor (professor).contains(discipline);*/
		
		professorBO.setProfessor (professor);
		return professorBO.canTeachDiscipline(discipline);
	}
	
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		String str = dt.format(DateTimeFormatter.ofPattern("HH:mm"));
		System.out.println(str);
		
	}

}
