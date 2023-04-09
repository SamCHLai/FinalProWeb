package tw.badminton.api;

public class Member {
	private String ID;
	private String account;
	private String memberName;
	private String phoneNumber;
	private String gender;
	private String birthday;
	
	public Member() {
		
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getID() {
		return ID;
	}

	public String getAccount() {
		return account;
	}

	
	

}