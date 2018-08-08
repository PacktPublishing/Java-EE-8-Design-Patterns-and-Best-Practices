package book.jeepatterns.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TestRevisionTO implements Serializable {
	
	private String enrollment;
	private String disciplineCode;
	private LocalDateTime testDateTime;
	public String getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	public String getDisciplineCode() {
		return disciplineCode;
	}
	public void setDisciplineCode(String disciplineCode) {
		this.disciplineCode = disciplineCode;
	}
	public LocalDateTime getTestDateTime() {
		return testDateTime;
	}
	public void setTestDateTime(LocalDateTime testDateTime) {
		this.testDateTime = testDateTime;
	}
	

}
