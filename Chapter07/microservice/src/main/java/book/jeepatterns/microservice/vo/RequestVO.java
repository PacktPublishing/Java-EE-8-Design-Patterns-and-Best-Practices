package book.jeepatterns.microservice.vo;

public class RequestVO {
	private String userCode;
	private String userName;
	private double value;
	private long registerNumber;
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}
	

}
