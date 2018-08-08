package book.jeepatterns.model;

import java.time.LocalTime;

public class Employee extends Member {
	
	private LocalTime entryTime;
	private LocalTime exitTime;
	
	public LocalTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}
	
	
	
	

}
