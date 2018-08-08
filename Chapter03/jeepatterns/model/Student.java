package book.jeepatterns.model;

public class Student extends Member {
	
	private String enrollment;
	
	public Student() {
	}

	public Student(String enrollment) {
		this.setEnrollment(enrollment);
	}
	
	public Student(String enrollment, String name) {
		this.setEnrollment(enrollment);
		this.setName(name);
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
}
