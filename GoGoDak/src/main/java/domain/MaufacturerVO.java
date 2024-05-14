package domain;

public class MaufacturerVO {
	
	private int maufacturer_seq;		//제조업체고유번호
	private String maufacturer_naem;	//제조업체이름
	private String maufacturer_tel;		//제조업체번호
	private String location;			//제조업체위치
	
	
	public int getMaufacturer_seq() {
		return maufacturer_seq;
	}
	public void setMaufacturer_seq(int maufacturer_seq) {
		this.maufacturer_seq = maufacturer_seq;
	}
	public String getMaufacturer_naem() {
		return maufacturer_naem;
	}
	public void setMaufacturer_naem(String maufacturer_naem) {
		this.maufacturer_naem = maufacturer_naem;
	}
	public String getMaufacturer_tel() {
		return maufacturer_tel;
	}
	public void setMaufacturer_tel(String maufacturer_tel) {
		this.maufacturer_tel = maufacturer_tel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
