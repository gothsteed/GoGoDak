package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MemberVO {
	
	private int member_seq;				//유저고유번호
    private String id;					//유저아이디
    private String password;			//유저비밀번호
    private String name;				//이름
    private String tel;					//핸드폰번호
    private String email;				//이메일
    private String jubun;				//주민등록번호
    private String postcode;
    private String address;
    private String address_detail;
    private String address_extra;
    private int point;				//포인트
    private Date registerDate;			//가입일자
    private int exist_status;			//회원탈퇴유무
    private int active_status;			//휴먼유무
    private Date last_password_change;	//마지막비밀번호 변경일
    private boolean isRequirePasswordChange;
  
    
    
    
    
    
	
    
    
    public boolean isRequirePasswordChange() {
		return isRequirePasswordChange;
	}

	public void setRequirePasswordChange(boolean isRequirePasswordChange) {
		this.isRequirePasswordChange = isRequirePasswordChange;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJubun() {
		return jubun;
	}

	public void setJubun(String jubun) {
		this.jubun = jubun;
	}
	

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_extra() {
		return address_extra;
	}

	public void setAddress_extra(String address_extra) {
		this.address_extra = address_extra;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getExist_status() {
		return exist_status;
	}

	public void setExist_status(int exist_status) {
		this.exist_status = exist_status;
	}

	public int getActive_status() {
		return active_status;
	}

	public void setActive_status(int active_status) {
		this.active_status = active_status;
	}

	public Date getLast_password_change() {
		return last_password_change;
	}

	public void setLast_password_change(Date last_password_change) {
		this.last_password_change = last_password_change;
	}



	public String getGender() {
        if(jubun.charAt(6) == '1' || jubun.charAt(6) == '3'){
            return "MALE";
        }

        return "FEMALE";
    }

    public int getAge() {

        String birthDateString = jubun.substring(0, 6);

        if(jubun.charAt(6) == '1' || jubun.charAt(6) == '2') {
            birthDateString  = "19" + birthDateString;
        }
        else {
            birthDateString  = "20" + birthDateString;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(birthDateString, dateTimeFormatter);

        LocalDate currentDate = LocalDate.now();

        Period agePeriod = Period.between(birthDate, currentDate);

        return  agePeriod.getYears();
    }
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}


	

	




}