package book.jeepatterns.microservice.vo;

public class ResponseVO {
	private String codeBar;
	private String userName;
	private String status;
	private long registerNumber;
	private double value;
	
	// ...some other information
	public String getCodeBar() {
		return codeBar;
	}
	public void setCodeBar(String codeBar) {
		this.codeBar = codeBar;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
	// ...
	
}
