package book.jeepatterns.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import book.jeepatterns.model.Course;
import book.jeepatterns.model.Student;

public class StudentDAO {
	
	public static enum FINANCIAL_STATUS {
		OK (true, "OK"), PENDING (false, "Payment pending"), DOC_PENDING (true, "Document pending");
		
		private boolean status;
		private String description;
		
		public boolean isStatus() {
			return status;
		}

		public String getDescription() {
			return description;
		}

		FINANCIAL_STATUS (boolean status, String description){
			this.status = status;
			this.description = description;
		}
	}
	
	public static enum ACADEMIC_STATUS {
		APPROVED , FAILED;
	}
	
	private static List<Student> students;
	private static Map<String, FINANCIAL_STATUS> studentStatusPayment = new HashMap<>();
	private static Map<Student, List<String>> studentXCourseName = new HashMap<>();
	
	static {
		Student s1 = new Student ("20010001", "student 1");
		Student s2 = new Student ("20010002", "student 2");
		Student s3 = new Student ("20010003", "student 3");
		Student s4 = new Student ("20010004", "student 4");
		students = Arrays.asList(s1, s2, s3, s4); 
	
		studentStatusPayment.put ("20010001", FINANCIAL_STATUS.OK);
		studentStatusPayment.put ("20010002", FINANCIAL_STATUS.OK);
		studentStatusPayment.put ("20010003", FINANCIAL_STATUS.PENDING);
		studentStatusPayment.put ("20010004", FINANCIAL_STATUS.OK);
	
		studentXCourseName.put (s1, Arrays.asList ("C01", "C02"));
		studentXCourseName.put (s2, Arrays.asList ("C03"));
		studentXCourseName.put (s3, Arrays.asList ("C04"));
		studentXCourseName.put (s4, Arrays.asList ("C03", "C04"));
	}
	
	public static Map<String, FINANCIAL_STATUS> getStudentStatusPayment() {
		return studentStatusPayment;
	}
	
	public List<Student> getEnrolledStudentByCourse(Course course) {
		return  studentXCourseName.keySet()
				.stream()
				.filter(s->studentXCourseName.get(s).contains(course.getCode()))
				.collect(Collectors.toList());
	}
	
	public Student getStudentByEnrollment (String enrollment) {
		return students
		.stream()
		.filter(s->s.getEnrollment().equals(enrollment))
		.findAny()
		.get();
	}
	
	public FINANCIAL_STATUS getStudentFinancialStatus (String enrollment) {
		return StudentDAO.studentStatusPayment.get (enrollment);
	}
	
}
