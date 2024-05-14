package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MemberVO {
	
    private String id;
    private String password;
    private String name;
    private String tel;
    private String email;
    private String poscode;
    private String address;
    private String addressDetail;
    private String addressExtra;
    private String jubun;
    private String point;
    private Date registerDate;
    private int status;
    private int idleStatus;
    private Date lastLoginDate;
    
    
    
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPoscode() {
		return poscode;
	}
	public void setPoscode(String poscode) {
		this.poscode = poscode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getAddressExtra() {
		return addressExtra;
	}
	public void setAddressExtra(String addressExtra) {
		this.addressExtra = addressExtra;
	}
	public String getJubun() {
		return jubun;
	}
	public void setJubun(String jubun) {
		this.jubun = jubun;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdleStatus() {
		return idleStatus;
	}
	public void setIdleStatus(int idleStatus) {
		this.idleStatus = idleStatus;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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



}
