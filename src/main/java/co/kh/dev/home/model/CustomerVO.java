package co.kh.dev.home.model;

public class CustomerVO {
 private String id;
 private String pwd;
 private String name;
 private String nickName;
 private String email;
 private String tel;
 private String phone;
 private String birth;
 private String zipCode;
 private String address1;
 private String address2;

 // 기본 생성자
 public CustomerVO() {}

 // 생성자
 public CustomerVO(String id, String pwd, String name, String nickName, String email, 
                   String tel, String phone, String birth, String zipCode, 
                   String address1, String address2) {
     this.id = id;
     this.pwd = pwd;
     this.name = name;
     this.nickName = nickName;
     this.email = email;
     this.tel = tel;
     this.phone = phone;
     this.birth = birth;
     this.zipCode = zipCode;
     this.address1 = address1;
     this.address2 = address2;
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getNickName() {
	return nickName;
}

public void setNickName(String nickName) {
	this.nickName = nickName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getBirth() {
	return birth;
}

public void setBirth(String birth) {
	this.birth = birth;
}

public String getZipcode() {
	return zipCode;
}



@Override
public String toString() {
	return "CustomerVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickName=" + nickName + ", email=" + email
			+ ", tel=" + tel + ", phone=" + phone + ", birth=" + birth + ", zipCode=" + zipCode + ", address1="
			+ address1 + ", address2=" + address2 + "]";
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getAddress1() {
	return address1;
}

public void setAddress1(String address1) {
	this.address1 = address1;
}

public String getAddress2() {
	return address2;
}

public void setAddress2(String address2) {
	this.address2 = address2;
}

}

