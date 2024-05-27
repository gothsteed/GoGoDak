package domain;

public class ManufacturerVO {
	
	private int manufacturer_seq;	  // 제조업체고유번호
	private String manufacturer_name; // 제조업체이름
	private String manufacturer_tel ; // 제조업체번호
	private String location;	      // 제조업체위치
	
	public int getManufacturer_seq() {
		return manufacturer_seq;
	}
	public void setManufacturer_seq(int manufacturer_seq) {
		this.manufacturer_seq = manufacturer_seq;
	}
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
	public String getManufacturer_tel() {
		return manufacturer_tel;
	}
	public void setManufacturer_tel(String manufacturer_tel) {
		this.manufacturer_tel = manufacturer_tel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
