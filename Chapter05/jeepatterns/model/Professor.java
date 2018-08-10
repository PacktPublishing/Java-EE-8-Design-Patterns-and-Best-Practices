package book.jeepatterns.model;

import java.time.LocalDate;

public class Professor extends Member {
	
	private LocalDate initTeachDate;
	
	public Professor() {
	}
	
	public Professor(String name, LocalDate initDate) {
		this.setName(name);
		this.setInitDate(initDate);
	}
	
	
	public Professor(String name) {
		this.setName(name);
	}

	public LocalDate getInitTeachDate() {
		return initTeachDate;
	}

	public void setInitTeachDate(LocalDate initTeachDate) {
		this.initTeachDate = initTeachDate;
	}
}
