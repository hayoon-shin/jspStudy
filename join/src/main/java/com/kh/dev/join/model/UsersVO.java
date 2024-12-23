package com.kh.dev.join.model;

public class UsersVO {
	private String id;            // -- 사용자 고유 ID
    private String pwd;           // -- 비밀번호
    private String pwdconfirm;    // -- 비밀번호 재확인
    private String name;          // -- 이름
    private int year;             // -- 생년 (4자리)
    private int month;            // -- 생월
    private int day;       		  // -- 생일
    private String gender;		  // -- 성별
    private String email;         // -- 이메일 (중복 방지)
    private String phone1;        // -- 전화 국가 코드
    private String phone2;           // -- 전화번호
    private String phone3;           // -- 인증번호
	
    public UsersVO() {
		super();
	}

	public UsersVO(String id, String pwd, String pwdconfirm, String name, int year, int month, int day, String gender,
			String email, String phone1, String phone2, String phone3) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.pwdconfirm = pwdconfirm;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdconfirm() {
		return pwdconfirm;
	}

	public void setPwdconfirm(String pwdconfirm) {
		this.pwdconfirm = pwdconfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	@Override
	public String toString() {
		return "UsersVO [id=" + id + ", pwd=" + pwd + ", pwdconfirm=" + pwdconfirm + ", name=" + name + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", gender=" + gender + ", email=" + email + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", phone3=" + phone3 + "]";
	}

}
